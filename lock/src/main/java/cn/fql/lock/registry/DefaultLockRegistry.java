package cn.fql.lock.registry;

/**
 * Created by fuquanlin on 06/12/2017.
 */

import org.springframework.util.Assert;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Default implementation of {@link LockRegistry} which uses Masked Hashcode algorithm to obtain locks.
 * When an instance of this class is created and array of {@link Lock} objects is created. The length of
 * the array is based on the 'mask' parameter passed in the constructor. The default mask is 0xFF which will create
 * and array consisting of 256 {@link ReentrantLock} instances.
 * When the {@link #obtain(Object)} method is called with the lockKey (e.g., Object) the index of the {@link Lock}
 * is determined by masking the object's hashCode (e.g., {@code object.hashCode & mask}) and the {@link Lock} is returned.
 *
 * @author Oleg Zhurakousky
 * @author Gary Russell
 * @since 2.1.1
 *
 */
public final class DefaultLockRegistry implements LockRegistry {

    private final Lock[] lockTable;

    private final int mask;

    /**
     * Constructs a DefaultLockRegistry with the default
     * mask 0xFF with 256 locks.
     */
    public DefaultLockRegistry(){
        this(0xFF);
    }

    /**
     * Constructs a DefaultLockRegistry with the supplied
     * mask - the mask must have a value Math.pow(2, n) - 1 where n
     * is 1 to 31, creating a hash of Math.pow(2, n) locks.
     * <p> Examples:
     * <ul>
     * <li>0x3ff (1023) - 1024 locks</li>
     * <li>0xfff (4095) - 4096 locks</li>
     * </ul>
     * @param mask The bit mask.
     */
    public DefaultLockRegistry(int mask){
        String bits = Integer.toBinaryString(mask);
        Assert.isTrue(bits.length() < 32 && (mask == 0 || bits.lastIndexOf('0') < bits.indexOf('1') ), "Mask must be a power of 2 - 1");
        this.mask = mask;
        int arraySize = this.mask+1;
        lockTable = new ReentrantLock[arraySize];
        for (int i = 0; i < arraySize; i++) {
            lockTable[i] = new ReentrantLock();
        }
    }

    /**
     * Obtains a lock by masking the lockKey's hashCode() with
     * the mask and using the result as an index to the lock table.
     * @param lockKey the object used to derive the lock index.
     */
    @Override
    public Lock obtain(Object lockKey) {
        Assert.notNull(lockKey, "'lockKey' must not be null");
        Integer lockIndex = lockKey.hashCode() & this.mask;
        return this.lockTable[lockIndex];
    }
}

