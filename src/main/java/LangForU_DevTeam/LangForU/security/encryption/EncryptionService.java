package LangForU_DevTeam.LangForU.security.encryption;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;

/**
 * Сервиз за криптиране и декриптиране на данни.
 * Използва AES/GCM/NoPadding с 256-битов ключ.
 */
@Service
public class EncryptionService {

    @Value("${encryption.key}")
    private String secretKey;

    @Value("${encryption.salt}")
    private String salt;

    private TextEncryptor textEncryptor;

    /**
     * Инициализира TextEncryptor след инжектиране на зависимостите.
     */
    @PostConstruct
    public void init() {
        this.textEncryptor = Encryptors.text(secretKey, salt);
    }

    /**
     * Криптира подаден низ.
     * @param data Низ за криптиране.
     * @return Криптиран низ.
     */
    public String encrypt(String data) {
        if (data == null) {
            return null;
        }
        return textEncryptor.encrypt(data);
    }

    /**
     * Декриптира подаден криптиран низ.
     * @param encryptedData Криптиран низ.
     * @return Оригиналният низ.
     */
    public String decrypt(String encryptedData) {
        if (encryptedData == null) {
            return null;
        }
        return textEncryptor.decrypt(encryptedData);
    }
}