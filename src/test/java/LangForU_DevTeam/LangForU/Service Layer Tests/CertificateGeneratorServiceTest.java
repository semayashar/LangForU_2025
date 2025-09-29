package LangForU_DevTeam.LangForU.certificate;

import LangForU_DevTeam.LangForU.appuser.AppUser;
import LangForU_DevTeam.LangForU.courses.Course;
import LangForU_DevTeam.LangForU.lections.Lection;
import LangForU_DevTeam.LangForU.security.encryption.EncryptionService;
import LangForU_DevTeam.LangForU.singUpForCourse.UserCourseRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Unit тестове за класа CertificateGeneratorService.
 * Използваме Mockito за симулиране на зависимости (EncryptionService).
 */
@ExtendWith(MockitoExtension.class) // Активира поддръжката на Mockito анотации
class CertificateGeneratorServiceTest {

    // 1. Декларираме "фалшив" обект (mock) за зависимостта.
    @Mock
    private EncryptionService mockEncryptionService;

    private CertificateGeneratorService certificateGeneratorService;

    @BeforeEach
    void setUp() {
        // 2. Преди всеки тест създаваме нова инстанция на нашия сервиз,
        // като му подаваме "фалшивия" EncryptionService.
        certificateGeneratorService = new CertificateGeneratorService(mockEncryptionService);
    }

    @Test
    void generateCertificateAsBytes_fromValidRequest_shouldReturnValidPdfByteArray() {
        // --- Arrange (Подготовка) ---
        // 1. Създаваме тестови данни
        AppUser user = new AppUser();
        user.setName("Мария Петрова");
        user.setEmail("maria@test.com"); // Добра практика е да имаме и email за логване на грешки

        Course course = new Course();
        course.setLanguage("Немски език B1");
        course.setStartDate(LocalDate.of(2025, 2, 1));
        course.setEndDate(LocalDate.of(2025, 5, 1));
        course.setLections(List.of(new Lection(), new Lection())); // 2 лекции

        String plainEgn = "9988776655";
        String encryptedEgn = "a1b2c3d4e5f6g7h8i9j0k1l2m3n4o5p6"; // Примерна криптирана стойност

        UserCourseRequest request = new UserCourseRequest();
        request.setUser(user);
        request.setCourse(course);
        request.setPIN(encryptedEgn); // В заявката ЕГН-то идва криптирано

        // 2. Конфигурираме поведението на "фалшивия" обект:
        // "Когато някой извика decrypt с криптирания ЕГН, върни чистия ЕГН"
        when(mockEncryptionService.decrypt(encryptedEgn)).thenReturn(plainEgn);

        // --- Act (Действие) ---
        // Изпълняваме метода, който тестваме
        byte[] pdfBytes = certificateGeneratorService.generateCertificateAsBytes(request, 85);

        // --- Assert (Проверка) ---
        // 1. Проверяваме дали резултатът не е null.
        assertNotNull(pdfBytes);
        // 2. Проверяваме дали масивът има съдържание.
        assertTrue(pdfBytes.length > 0);
        // 3. Проверяваме дали файлът започва със сигнатурата за PDF ("магически байтове").
        String pdfMagicNumber = new String(pdfBytes, 0, 5);
        assertEquals("%PDF-", pdfMagicNumber);
    }

    @Test
    void generateCertificateAsBytes_whenRequestIsMissingUser_shouldReturnNull() {
        // --- Arrange ---
        // Симулираме грешка, като подаваме заявка с липсващи данни (user е null),
        // което ще предизвика NullPointerException вътре в метода.
        UserCourseRequest incompleteRequest = new UserCourseRequest();
        incompleteRequest.setCourse(new Course()); // User остава null
        incompleteRequest.setPIN("some-encrypted-pin");

        // --- Act ---
        // Методът трябва да "хване" грешката и да върне null, както е написано в catch блока му.
        byte[] pdfBytes = certificateGeneratorService.generateCertificateAsBytes(incompleteRequest, 85);

        // --- Assert ---
        // Проверяваме дали резултатът е null, както очакваме.
        assertNull(pdfBytes);
    }

    @Test
    void generateCertificateAsBytes_whenEncryptionFails_shouldReturnNull() {
        // --- Arrange ---
        UserCourseRequest request = new UserCourseRequest();
        request.setUser(new AppUser());
        request.setCourse(new Course());
        String encryptedEgn = "some-encrypted-string";
        request.setPIN(encryptedEgn);

        // Конфигурираме "фалшивия" обект да хвърли грешка при опит за декриптиране
        when(mockEncryptionService.decrypt(encryptedEgn)).thenThrow(new RuntimeException("Decryption error for test"));

        // --- Act ---
        byte[] pdfBytes = certificateGeneratorService.generateCertificateAsBytes(request, 90);

        // --- Assert ---
        // Очакваме сервизът да улови грешката и да върне null
        assertNull(pdfBytes);
    }
}
