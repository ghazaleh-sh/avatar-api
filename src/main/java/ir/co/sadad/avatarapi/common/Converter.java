package ir.co.sadad.avatarapi.common;

import org.apache.commons.codec.binary.Base64;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;

/**
 * class for common method of convert
 */
public class Converter {

    /**
     * method for convert to inputStream
     *
     * @param file
     * @return
     */
//    public static byte[] toByteArray(String file) {
//        String base64Image = file.split(",")[1];
//        return javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
//    }

    public static String decodeJWTBody(String JWTToken) {
        String[] split_string = JWTToken.split("\\.");
        String base64EncodedBody = split_string[1];
        Base64 base64Url = new Base64(true);

        return new String(base64Url.decode(base64EncodedBody));
    }

    public static byte[] extractBytes(DataBuffer dataBuffer) {
        byte[] bytes = new byte[dataBuffer.readableByteCount()];
        dataBuffer.read(bytes);
        DataBufferUtils.release(dataBuffer);

        return bytes;
    }
}
