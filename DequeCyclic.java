import CITS2200.Deque;
import CITS2200.Overflow;
import CITS2200.Underflow;
@SuppressWarnings("unchecked") //Could not get block arr to work without casting had to suppress warning


public class DequeCyclic<E> implements Deque<E> {
    private E[] block;
    private int before, after, SIZE;

    /**
     * Constructor
     */
    public DequeCyclic(int size) {
        block = (E[]) new Object[size];
        before = 0;
        after = -1;
        SIZE = size;
    }

    /**
     * checks is block is empty
     */
    public boolean isEmpty() {
        return (after == -1);
    }

    /**
     * checks if block is full
     */
    public boolean isFull() {
        boolean full = after == before + 1;
        if ((before == SIZE - 1) && (after == 0)){
            full = true;
        }

        return full;

    }
    /**
     * puts a new item with type E to the after of block
     */
    public void pushLeft(E item) throws Overflow {
        if (!isFull()){
            if (after == -1){
                after = 0;
                before = 0;
            }else if (after == 0) {
                after = SIZE -1;
            }else{
                after--;
            }
            block[after] = item;
        } else {
            throw new Overflow("Queue is Full");
        }

    }
    /**
     * puts a new item to back of block
     */
    public void pushRight(E item) throws Overflow {
        if (!isFull()) {
            if (after == -1){
                after = 0;
                before = 0;
            }else if(before == SIZE - 1) {
                before = 0;
            } else {
                before = before + 1;
            }
            block[before] = item;
        }else{
            throw new Overflow("Queue is Full");
        }

    }
    /**
     * returns the left most item in block
     */
    public E peekLeft() throws Underflow {
        if (!isEmpty()){
            return block[after];
        } else {
            throw new Underflow("Queue is empty");
        }
    }
    /**
     * returns the rightmost item in block
     */
    public E peekRight() throws Underflow {

        if (isEmpty() || before < 0){
            throw new Underflow("Queue is empty");
        } else {
            return block[before];
        }
    }
    /**
     * deletes and returns the leftmost item in block
     */
    public E popLeft() throws Underflow {

        if (!isEmpty()) {
            E item = block[after];
            if (after == before) {
                after = -1;
                before = -1;
            }else if (after == SIZE - 1) {
                after = 0;
            }
            else {
                after = after + 1;
            }
            return item;
        }else{
            throw new Underflow("Queue Empty");
        }

    }
    /**
     * deletes and returns the rightmost item in block
     */
    public E popRight() throws Underflow {

        if (!isEmpty()) {
            E item = block[before];
            if (after == before) {
                after = -1;
                before = -1;
            } else if (before == 0) {
                before = SIZE - 1;
            }
            else{
                before = before - 1;
            }
            return item;
        } else{
            throw new Underflow("Queue is empty");
        }

    }
}