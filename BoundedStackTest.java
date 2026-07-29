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
        System.out.println("-- Creators (capacity) --");
        
        // สร้าง BoundedStack ที่ว่างและยังไม่เต็ม แม้ capacity จะเล็กมาก
        BoundedStack s = new BoundedStack(1);
        check("new() -> size = 0", s.size() == 0);
        check("new() -> isEmpty = true", s.isEmpty());

        // boundary: เช็คว่าตอนพึ่งสร้างเสร็จยังว่างและยังไม่เต็ม แม้ capacity = 1
        BoundedStack s1 = new BoundedStack(1);
        check("capacity=1 stack is empty initially", s1.isEmpty());
        check("capacity=1 stack is not full before push", !s1.isFull());


        // เช็คจำนวนข้อมูลที่pushเข้ามา และเช็คว่าไม่ที่เก็บข้อมูลว่าง
        BoundedStack data = new BoundedStack(3);
        data.push("b6821651531");
        data.push("b6821651621");
        check("ข้อมูลถูก push เข้ามา 2 ตัว -> size = 2", data.size() == 2);
        check("ข้อมูลถูก push เข้ามา 2 ตัว -> isEmpty = false", !data.isEmpty());

        // input ที่ผิดเงื่อนไขและโยนExceptionออกไป
        boolean threw = false;
        try {
            new BoundedStack(0);
        } catch (IllegalArgumentException e) {
            threw = true;
        }
        check("new(capacity=0) -> throws IllegalArgumentException", threw);

        // เช็คว่า capacity ที่ส่งเข้าไปตอนสร้างถูกใช้งานจริง
        BoundedStack capacitys = new BoundedStack(2);
        capacitys.push("b6821651531");
        capacitys.push("b6821651621");
        check("capacity=2 stack is full after 2 pushes", capacitys.isFull());
        
    }

    // Push ข้อมูลที่ผิดเงื่อนไข
    private static void testPush() {
        System.out.println("\n-- Push --");

        //เช็คการ push ข้อมูลที่ถูกต้องและเช็คว่า size เพิ่มขึ้นและ peek คืนค่าตัวล่าสุด
        BoundedStack s = new BoundedStack(3);
        s.push("b6821651621");
        check("ข้อมูลที่เพิ่มล่าสุด -> size 1", s.size() == 1);
        check("ข้อมูลที่เพิ่มล่าสุด -> peek b6821651621", s.peek().equals("b6821651621"));

        // เพิ่มข้อมูลหลายตัวและเช็คว่าpeek คืนค่าตัวล่าสุดเสมอ
        s.push("b6821651531");
        s.push("b6821651003");
        check("ข้อมูลที่เพิ่มล่าสุด -> peek b6821651003", s.peek().equals("b6821651003"));

        // input ที่ผิดเงื่อนไขต้องโยน exception
        boolean threwEmpty = false;
        try {
            s.push(""); 
        } catch (IllegalArgumentException e) {
            threwEmpty = true;
        }
        check("push(empty string) -> throws IllegalArgumentException", threwEmpty);

        boolean threwNull = false;
        try {
            s.push(null);  
        } catch (IllegalArgumentException e) {
            threwNull = true;
        }
        check("push(null) -> throws IllegalArgumentException", threwNull);
        check("failed push(null) leaves stack unchanged", s.size() == 3);

        // Push จนเต็ม แล้วPushเข้าไปเพิ่ม
        BoundedStack full = new BoundedStack(1);
        full.push("b6821651531");

        boolean threwFull = false;
        try {
            full.push("b6821650005");
        } catch (IllegalStateException e) {
            threwFull = true;
        }
        check("push when full -> throws IllegalStateException", threwFull);
        check("Size and Peek at capacity", full.size() == 1 && full.peek().equals("b6821651531"));
    }

    // การ Pop ข้อมูลที่ผิดเงื่อนไข
    public static void testPop() {
        System.out.println("\n-- Pop --");

        // ถ้าไม่มีข้อมูล ให้โยน exception ออกไป
        BoundedStack empty = new BoundedStack(3);
        boolean threwEmpty = false;
        try {
            empty.pop();
        } catch (IllegalStateException e) {
            threwEmpty = true;
        }
        check("pop() on empty -> throws IllegalStateException", threwEmpty);

        // เช็คการดึงข้อมูลตัวล่าสุดออกมาและมีพื้นที่ว่างเพิ่มขึ้น
        empty.push("b6821651531"); 
        empty.push("b6821651621");
        empty.push("b6821650003");
        check("pop() -> size = 3", empty.size() == 3);

        // ดึงข้อมูลตัวล่าสุดออกมา
        check("pop() -> returns b6821650003", empty.pop().equals("b6821650003"));
        check("pop() -> size = 2", empty.size() == 2);

        // เหลือข้อมูลตัวแรก
        check("peek() -> b6821651621", empty.peek().equals("b6821651621"));

        // เมื่อ stack ว่าง แล้วเรียก pop() ต้องโยนให้ IllegalStateException
        empty.pop();
        empty.pop();
        boolean threw = false;
        try {
            empty.pop();
        } catch (IllegalStateException e) {
            threw = true;
        }
        check("pop() on empty -> throws IllegalStateException", threw);

    }

    // การอ่านข้อมูลที่ตำแหน่งสุดท้าย
    private static void testpeek() {
        System.out.println("\n-- Peek --");

        // ถ้า Stack ว่าง แล้วเรียก peek() ต้องโยน IllegalStateException
        BoundedStack empty = new BoundedStack(3);
        boolean threwEmpty = false;
        try {
            empty.peek();
        } catch (IllegalStateException e) {
            threwEmpty = true;
        }
        check("peek() on empty -> throws IllegalStateException", threwEmpty);

        // ถ้า Stack มีข้อมูล 1 ตัว peek ต้องคืนค่าตัวนั้น
        BoundedStack first = new BoundedStack(3);
        first.push("b6821651531");
        check("peek() one element -> b6821651531" , first.peek().equals("b6821651531"));

        // ถ้า Stack มีหลายตัว จะคืนค่าตัวบนสุด (ตัวที่ push ล่าสุด)
        BoundedStack many = new BoundedStack(3);
        many.push("b6821651531");
        many.push("b6821651621");
        many.push("b6821650003");
        check("peek() returns top element" , many.peek().equals("b6821650003"));

    }

    // --- Observer ต้องไม่มี side effect ความเป็นอื่น ---
    // Observer ไว้ตรวจเมธอด size , isEmpty , isFull , peek ว่าถ้ามีการเปลี่ยนแปลงโค้ด ข้อมูลใน Stack จะไม่เปลี่ยน
    private static void testObservers() {
        System.out.println("\n-- Observers --");

        BoundedStack s = new BoundedStack(3);
        s.push("b6821651531");
        s.push("b6821651621");

        int beforeSize = s.size();
        String beforeTop = s.peek();

        s.size();
        s.isEmpty();
        s.isFull();
        s.peek();

        check("Observers no side effects", s.size() == beforeSize && s.peek().equals(beforeTop));
    }

    // --- Producer ต้องคืนตัวใหม่ และไม่ส่งผลต่อตัวเดิม ---
    private static void testProducer() {
        System.out.println("\n-- Producer (copy) --");

        //สร้างoriginalที่มีข้อมูล 3 ตัว
        BoundedStack original = new BoundedStack(3);
        original.push("b6821651531");
        original.push("b6821651621");
        original.push("b6821650003");

        // copy() แล้วเช็คว่าได้ objectใหม่ที่ไม่ใช่ original
        BoundedStack copy = original.copy();
        check("copy is a new object", copy != original);

        // เช็คว่าแก้ไขข้อมูลใน copy แล้วไม่ส่งผลต่อ original
        check("copy has same top as original right after copy()", copy.peek().equals(original.peek()));
        copy.pop();
        check("pop() on copy does not affect original", copy.size() == 2 ); 
        check("original is unchanged", original.size() == 3);

        // เช็คว่าหลังpushหรือpop ข้อมูลยังเรียงถูกต้อง
        check("copy peek() is correct", copy.peek().equals("b6821651621"));

    }

    // --- ทดสอบว่าไม่เกิด representation exposure ---
    private static void testExposure() {

    }
}