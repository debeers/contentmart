package GeneralHelpers;

/**
 * Created by CMG_TEST on 14.09.2015.
 */
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.apache.commons.codec.digest.DigestUtils.md5Hex;
import static org.apache.commons.codec.digest.DigestUtils.shaHex;

public class CheckFileHash {

    private static final Logger LOG = Logger.getLogger(CheckFileHash.class);
    private HashType typeOfHash = null;
    private String expectedFileHash = null;
    private File fileToCheck = null;

    /**
     * The File to perform a Hash check upon
     *
     * @param fileToCheck
     * @throws FileNotFoundException
     */
    public void fileToCheck(File fileToCheck) throws FileNotFoundException {
        if (!fileToCheck.exists()) throw new FileNotFoundException(fileToCheck + " does not exist!");

        this.fileToCheck = fileToCheck;
    }

    /**
     * Hash details used to perform the Hash check
     *
     * @param hash
     * @param hashType
     */
    public void hashDetails(String hash, HashType hashType) {
        this.expectedFileHash = hash;
        this.typeOfHash = hashType;
    }

    /**
     * Performs a expectedFileHash check on a File.
     *
     * @return
     * @throws IOException
     */
    public boolean hasAValidHash() throws IOException {
        if (this.fileToCheck == null) throw new FileNotFoundException("File to check has not been set!");
        if (this.expectedFileHash == null || this.typeOfHash == null) throw new NullPointerException("Hash details have not been set!");

        String actualFileHash = "";
        boolean isHashValid = false;

        switch (this.typeOfHash) {
            case MD5:
                actualFileHash = md5Hex(new FileInputStream(this.fileToCheck));
                if (this.expectedFileHash.equals(actualFileHash)) isHashValid = true;
                break;
            case SHA1:
                actualFileHash = shaHex(new FileInputStream(this.fileToCheck));
                if (this.expectedFileHash.equals(actualFileHash)) isHashValid = true;
                break;
        }

        LOG.info("Filename = '" + this.fileToCheck.getName() + "'");
        LOG.info("Expected Hash = '" + this.expectedFileHash + "'");
        LOG.info("Actual Hash = '" + actualFileHash + "'");

        return isHashValid;
    }

}