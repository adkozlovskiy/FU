package kozlovskiy.prod.exceptions;

/**
 * Thrown when it was not possible to upload a file or create a storage.
 */
public class StorageException extends RuntimeException {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}