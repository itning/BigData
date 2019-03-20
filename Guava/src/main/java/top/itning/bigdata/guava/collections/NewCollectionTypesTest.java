package top.itning.bigdata.guava.collections;

import com.google.common.collect.*;
import org.junit.Test;
import sun.security.provider.certpath.Vertex;

import java.util.Comparator;
import java.util.Map;

/**
 * 新集合类型
 * Guava引入了很多JDK没有的、但我们发现明显有用的新集合类型。
 * 这些新类型是为了和JDK集合框架共存，而没有往JDK集合抽象中硬塞其他概念。
 * 作为一般规则，Guava集合非常精准地遵循了JDK接口契约。
 *
 * @author itning
 */
public class NewCollectionTypesTest {
    @Test
    public void multisetTest() {
        //Map                    对应的Multiset              是否支持null元素
        //HashMap                HashMultiset                是
        //TreeMap                TreeMultiset                是（如果comparator支持的话）
        //LinkedHashMap          LinkedHashMultiset      	 是
        //ConcurrentHashMap      ConcurrentHashMultiset      否
        //ImmutableMap           ImmutableMultiset           否

        //当把Multiset看作Map<E, Integer>时，它也提供了符合性能期望的查询操作：
        //
        //count(Object)返回给定元素的计数。HashMultiset.count的复杂度为O(1)，TreeMultiset.count的复杂度为O(log n)。
        //entrySet()返回Set<Multiset.Entry<E>>，和Map的entrySet类似。
        //elementSet()返回所有不重复元素的Set<E>，和Map的keySet()类似。
        //所有Multiset实现的内存消耗随着不重复元素的个数线性增长。
    }

    @Test
    public void sortedMultisetTest() {
        //TreeMultiset实现SortedMultiset接口。
        TreeMultiset<Integer> multiset = new TreeMultiset<>((Comparator<Integer>) (o1, o2) -> {
            if (o1 > o2) {
                return 1;
            } else if (o1 < o2) {
                return -1;
            } else {
                return 0;
            }
        });
    }

    @Test
    public void multimapTest() {
        //实现                    键行为类似            值行为类似
        //ArrayListMultimap        HashMap              ArrayList
        //HashMultimap             HashMap              HashSet
        //LinkedListMultimap*      LinkedHashMap*       LinkedList*
        //LinkedHashMultimap**     LinkedHashMap        LinkedHashMap
        //TreeMultimap             TreeMap              TreeSet
        //ImmutableListMultimap    ImmutableMap         ImmutableList
        //ImmutableSetMultimap     ImmutableMap         ImmutableSet

        //除了两个不可变形式的实现，其他所有实现都支持null键和null值
    }

    @Test
    public void biMapTest() {
        Map<String, Integer> nameToId = Maps.newHashMap();
        Map<Integer, String> idToName = Maps.newHashMap();
        nameToId.put("Bob", 42);
        idToName.put(42, "Bob");
        //如果"Bob"和42已经在map中了，会发生什么?
        //如果我们忘了同步两个map，会有诡异的bug发生...
        //BiMap<K, V>是特殊的Map：
        //
        //可以用 inverse()反转BiMap<K, V>的键值映射
        //保证值是唯一的，因此 values()返回Set而不是普通的Collection
        //在BiMap中，如果你想把键映射到已经存在的值，会抛出IllegalArgumentException异常。
        //如果对特定值，你想要强制替换它的键，请使用 BiMap.forcePut(key, value)。
    }

    @Test
    public void tableTest() {
        Table<Vertex, Vertex, Double> weightedGraph = HashBasedTable.create();
        // weightedGraph.put(v1, v2, 4);
        // weightedGraph.put(v1, v3, 20);
        // weightedGraph.put(v2, v3, 5);
        // weightedGraph.row(v1); // returns a Map mapping v2 to 4, v3 to 20
        // weightedGraph.column(v3); // returns a Map mapping v1 to 20, v2 to 5

        //通常来说，当你想使用多个键做索引的时候，你可能会用类似Map<FirstName, Map<LastName, Person>>的实现，
        //这种方式很丑陋，使用上也不友好。Guava为此提供了新集合类型Table，它有两个支持所有类型的键：”行”和”列”。
        //Table提供多种视图，以便你从各种角度使用它
    }

    @Test
    public void classToInstanceMapTest() {
        //ClassToInstanceMap是一种特殊的Map：它的键是类型，而值是符合键所指类型的对象。
        ClassToInstanceMap<Number> numberDefaults=MutableClassToInstanceMap.create();
        numberDefaults.putInstance(Integer.class, Integer.valueOf(0));
    }

    @Test
    public void rangeSetTest(){
        //RangeSet描述了一组不相连的、非空的区间。
        //当把一个区间添加到可变的RangeSet时，所有相连的区间会被合并，空区间会被忽略。
        RangeSet<Integer> rangeSet = TreeRangeSet.create();
        rangeSet.add(Range.closed(1, 10)); // {[1,10]}
        rangeSet.add(Range.closedOpen(11, 15));//不相连区间:{[1,10], [11,15)}
        rangeSet.add(Range.closedOpen(15, 20)); //相连区间; {[1,10], [11,20)}
        rangeSet.add(Range.openClosed(0, 0)); //空区间; {[1,10], [11,20)}
        rangeSet.remove(Range.open(5, 10)); //分割[1, 10]; {[1,5], [10,10], [11,20)}
    }

    @Test
    public void rangeMapTest(){
        //RangeMap描述了”不相交的、非空的区间”到特定值的映射。
        //和RangeSet不同，RangeMap不会合并相邻的映射，即便相邻的区间映射到相同的值。
        RangeMap<Integer, String> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.closed(1, 10), "foo"); //{[1,10] => "foo"}
        rangeMap.put(Range.open(3, 6), "bar"); //{[1,3] => "foo", (3,6) => "bar", [6,10] => "foo"}
        rangeMap.put(Range.open(10, 20), "foo"); //{[1,3] => "foo", (3,6) => "bar", [6,10] => "foo", (10,20) => "foo"}
        rangeMap.remove(Range.closed(5, 11)); //{[1,3] => "foo", (3,5) => "bar", (11,20) => "foo"}
    }
}
