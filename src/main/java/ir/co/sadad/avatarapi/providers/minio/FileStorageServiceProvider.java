package ir.co.sadad.avatarapi.providers.minio;


import io.minio.errors.*;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * provide for storage services.
 * <pre>
 *     this service will store image on MinIO server and return it's url
 * </pre>
 * <pre>
 *     for key of url , we use
 * </pre>
 */
public interface FileStorageServiceProvider {


    InputStream getFile(String bucketName,String fileName) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException;

}