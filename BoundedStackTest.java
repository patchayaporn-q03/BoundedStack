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
        System.out.println("-- Creators --");

        
        // เริ่มต้นจากที่เก็บข้อมูลว่าง
        BoundedStack empty = new BoundedStack(3);
        check("new() -> size = 0", empty.size() == 0);
        check("new() -> isEmpty = true", empty.isEmpty());

        // เช็คจำนวนพนักงานที่ลงทะเบียนเข้ามา
        BoundedStack data = new BoundedStack(Arrays.asList("b6821651531", "b6821651621"));
        check("พนักงานลงทะเบียนมา 2 คน -> size = 2", data.size() == 2);
        check("พนักงานลงทะเบียนมา 2 คน -> isEmpty = false", !data.isEmpty());

        // input ที่ผิดเงื่อนไขและโยนExceptionออกไป
        boolean threwDup = false;
        try {
            new BoundedStack(Arrays.asList("b6821651531", "b6821651531"));
        } catch (IllegalArgumentException e) {
            threwDup = true;
        }
        check("new(duplicates) -> throws IllegalArgumentException", threwDup);

        boolean threwNull = false;
        try {
            new BoundedStack(Arrays.asList("b6821651531", null));
        } catch (IllegalArgumentException e) {
            threwNull = true;
        }
        check("new(list with null) -> throws IllegalArgumentException", threwNull);

        boolean threwNullList = false;
        try {
            new BoundedStack(null);
        } catch (IllegalArgumentException e) {
            threwNullList = true;
        }
        check("new(null) -> throws IllegalArgumentException", threwNullList);
    }

    // Push ข้อมูลที่ผิดเงื่อนไข
    private static void testPush() {
        System.out.println("\n-- Push --");

        BoundedStack s = new BoundedStack(3);
        s.push("b6821651621");
        check("push(b6821651621) -> size 1", s.size() == 1);
        check("push(b6821651621) -> peek EMP001", s.peek().equals("b6821651621"));

        s.push("b6821651531");
        s.push("b6821650003");
        check("push preserves insertion order", s.size() == 3);

        // input ที่ผิดเงื่อนไขต้องโยน exception
        // input ที่เป็นค่าว่าง
        boolean threwEmpty = false;
        try {
            s.push(""); 
        } catch (IllegalArgumentException e) {
            threwEmpty = true;
        }
        check("push(empty string) -> throws IllegalArgumentException", threwEmpty);

        // ยังไม่ใส่ข้อมูล input
        boolean threwNull = false;
        try {
            s.push(null);  
        } catch (IllegalArgumentException e) {
            threwNull = true;
        }
        check("push(null) -> throws IllegalArgumentException", threwNull);
        check("failed push(null) leaves stack unchanged", s.size() == 3);

        // Push จนเต็มพอดี แล้วเติมเพิ่ม
        BoundedStack full = new BoundedStack(3);

        for (int i = 1; i <= 3; i++) {
            full.push("b6821650003" + i);
        }

        check("stack is full", full.isFull());

        boolean threwFull = false;
        try {
            full.push("b6821650051");
        } catch (IllegalStateException e) {
            threwFull = true;
        }

        check("push when full -> throws IllegalStateException", threwFull);
        check("full stack stays at capacity", full.size() == 3);

    }

    // การ Pop ข้อมูลที่ผิดเงื่อนไข
    public static void testPop() {

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