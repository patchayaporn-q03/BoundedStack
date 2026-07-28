import java.util.*;

/**
 * ทำโดย 6821651531 พัชญาพร จันอุดม หมู่800 และ 6821651621 ภัทราภรณ์ หินขุนทด หมู่800
 * BoundedStack คือ
 * 
 * ตัวอย่างการใช้งาน:การเก็บรหัสพนักงานที่ลงทะเบียนเข้ามา
 *      BoundedStack s = new BoundedStack(50);
 *      s.push("b6821651531");
 *      s.push("b6821651621");
 *      String now = s.peek(); // peek() = ดูข้อมูลที่ถูก push เข้ามาล่าสุด คือ "b6821651621"
 *      String removed = s.pop(); // pop(); = ดึงข้อมูลที่ถูก push เข้ามาล่าสุดออก คือ "b6821651621"
 */
public class BoundedStack {

    private final List<String> data;
    private final int capacity;
    
    // AF(data, capacity) = Stackที่มีความจุสูงสุด capacity
    //  โดยเริ่มจาก data.get(0) เป็นข้อมูลตัวแรกที่ถูก push เข้ามา
    //  และ data.get(data.size()-1) เป็นข้อมูลตัวล่าสุดที่จะโดนนำออกตอน pop()
    //  และมีจำนวนข้อมูลปัจจุบันเท่ากับ data.size()

    // RI
    // - data != null
    // - capacity > 0
    // - data.size() <= capacity
    // - data.get(i) != null

    private void checkRep() {
        assert data != null : "data ต้องไม่เป็น null";
        assert capacity > 0 : "capacity ต้องมากกว่า 0";
        assert data.size() <= capacity : "จำนวนข้อมูลต้องน้อยกว่าหรือเท่ากับ capacity";
        for (String id : data) {
            assert id != null : "ข้อมูลไม่เป็น null";
        }
    }

    /**
     * สร้าง BoundedStack ว่างตามขนาดความจุที่กำหนด
     *
     * @param capacity ความจุสูงสุดที่เก็บข้อมูลได้ต้องมากกว่า 0
     * @throws IllegalArgumentException ถ้า capacity <= 0
     */
    public BoundedStack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity ต้องมากกว่า 0");
        }
        this.capacity = capacity;
        this.data = new ArrayList<>(capacity);
        checkRep();
    }

}