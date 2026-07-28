import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * 
 * BoundedStackTest คือ
 */
public class BoundedStackTest {
    private static int passed = 0;
    private static int failed = 0;

    /** helper กลาง — พิมพ์ PASS/FAIL และนับผลให้เอง */
    private static void check(String name, boolean condition) {
        if (condition) {
            passed++;
            System.out.println("[PASS] " + name);
        } else {
            failed++;
            System.out.println("[FAIL] " + name);
        }
        
    }
    public static void main(String[] args) {
        boolean assertsOn = false;
        assert assertsOn = true;
        if (!assertsOn) {
            System.out.println("WARNING: assertions disabled"
                    + " - re-run with: java -ea BoundedStackTest\n");
        }
        System.out.println("=== BoundedStack Test ===\n");

        testCreators();
        testPush();
        testPop();
        testpeek();
        testObservers();
        testProducer();
        testExposure();

        System.out.println("\n=== Summary ===");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        System.out.println("Total : " + (passed + failed));
        System.out.println(failed == 0 ? "ALL TESTS PASSED" : "SOME TESTS FAILED");
    }

    // --- Partition: ว่าง / จำนวนพนักงานที่ลงทะเบียนมา / input ที่ผิดเงื่อนไข ---
    private static void testCreators() {

    }

    // Push ข้อมูลที่ผิดเงื่อนไข
    private static void testPush() {

    }

    // การ Pop ข้อมูลที่ผิดเงื่อนไข
    public static void testPop(){

    }

    // การ
    private static void testpeek() {

    }

    // --- Observer ต้องไม่มี side effect ความเป็นอื่น ---
    private static void testObservers() {

    }

    // --- Producer ต้องคืนตัวใหม่ ไม่แก้ตัวเดิม ---
    private static void testProducer() {

    }

    // --- ทดสอบว่าไม่เกิด representation exposure ---
    private static void testExposure() {

    }

}