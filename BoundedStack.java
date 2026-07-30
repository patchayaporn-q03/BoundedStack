import java.util.*;

/**
 * ทำโดย 6821651531 พัชญาพร จันอุดม หมู่800 และ 6821651621 ภัทราภรณ์ หินขุนทด หมู่800
 * BoundedStack คือการเก็บข้อมูลในรูปแบบ Stack มีการกำหนด capacity เอาไว้ และการทำงานคือเข้าทีหลังออกก่อน (LIFO)
 * 
 * ตัวอย่างการใช้งาน:การเก็บรหัสพนักงานที่ลงทะเบียนเข้ามา
 *      BoundedStack s = new BoundedStack(50);
 *      s.push("b6821651531");
 *      s.push("b6821651621");
 *      String now = s.peek(); // peek() = ดูข้อมูลที่ถูก push เข้ามาล่าสุด คือ "b6821651621"
 *      String remove = s.pop(); // pop(); = ดึงข้อมูลที่ถูก push เข้ามาล่าสุดออก คือ "b6821651621"
 */
public class BoundedStack {

    private final List<String> data;
    private final int capacity;
    static final int MAX_LONGSTRING = 10000;
    
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

    /** 
     * @return จำนวนข้อมูลที่ถูก push เข้ามา
     */
    public int size() {
        return data.size();
    }

    /**
     * @return true ถ้ามีข้อมูล, false ถ้าไม่มีข้อมูล
     */
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * input ข้อมูลเข้ามาเก็บใน Stack
     * 
     * @param data ข้อมูลที่จะ input เข้ามา และต้องไม่เป็น null
     * @throws IllegalArgumentException ถ้า data เป็น null หรือเป็นสตริงว่าง
     * @throws IllegalStateException ถ้าเก็บข้อมูลเต็มแล้ว (size เท่ากับ capacity)
     */
    public void push(String data){
        if (data == null || data.isEmpty()) throw new IllegalArgumentException();
        if (data.length() > MAX_LONGSTRING) throw new IllegalArgumentException("data is long");
        if (isFull()) throw new IllegalStateException("Stack is full capacity");
        this.data.add(data);
        checkRep();
    }

    /**
     * นำข้อมูลที่ input เข้ามาล่าสุดออกจาก Stack แล้วคืนค่ากลับ
     * 
     * @return ข้อมูลที่ถูก push เข้ามาล่าสุด
     * @throws IllegalStateException ถ้า Stack ว่าง
     */
    public String pop(){
        if (data.isEmpty()) throw new IllegalStateException("Stack is empty");
        String remove = data.remove(data.size() - 1);
        checkRep();
        return remove;
    }

    /**
     * ดูข้อมูลที่ถูก push เข้ามาล่าสุด แต่ไม่ลบ
     * 
     * @return ข้อมูลที่ถูก push เข้ามาล่าสุด
     * @throws IllegalStateException ถ้า Stack ว่าง
     */
    public String peek(){
        if (data.isEmpty()) throw new IllegalStateException("Stack is empty");
        checkRep();
        return data.get(data.size() - 1);
    }

    /**
     * ดู Stack ว่าเต็มหรือยัง
     * 
     * @return true ถ้า Stack เต็มแล้ว (size เท่ากับ capacity)
     */
    public boolean isFull() {
        return data.size() == capacity;
    }

    /**
     * สร้าง BoundedStack ตัวใหม่ที่มีข้อมูลและ capacity เหมือนเดิม
     * ทำหน้าที่เป็นProducer
     * 
     * @return ข้อมูลใหม่ที่ copy ข้อมูลเดิมมา
     */
    public BoundedStack copy() {
        BoundedStack copy = new BoundedStack(this.capacity);
        copy.data.addAll(this.data);
        copy.checkRep();
        return copy;
    }

    /**
     * คืนสำเนาของข้อมูลทั้งหมดใน stack ตามลำดับปัจจุบัน (index 0 = ล่างสุด)
     * การแก้ไข list ที่คืนค่านี้จะไม่ส่งผลต่อ stack ต้นฉบับ
     * 
     * @return สำเนาของข้อมูล
     */
    public List<String> data() {
        return new ArrayList<>(this.data);
    }
}