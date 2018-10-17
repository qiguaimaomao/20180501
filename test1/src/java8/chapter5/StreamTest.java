package java8.chapter5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;import java.util.TooManyListenersException;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
public static void main(String[] args) {
	Trader raoul = new Trader("Raoul", "Cambridge");
	Trader mario = new Trader("Mario","Milan");
	Trader alan = new Trader("Alan","Cambridge");
	Trader brian = new Trader("Brian","Cambridge");
	List<Transaction> transactions = Arrays.asList(
	new Transaction(brian, 2011, 300),
	new Transaction(raoul, 2012, 1000),
	new Transaction(raoul, 2011, 400),
	new Transaction(mario, 2012, 710),
	new Transaction(mario, 2012, 700),
	new Transaction(alan, 2012, 950)
	);
	
	//(1) �ҳ�2011�귢�������н��ף��������׶����򣨴ӵ͵��ߣ�
	List<Transaction> list1=transactions.stream().filter(t -> t.getYear()==2011)
			.sorted(Comparator.comparing(Transaction::getValue))
			.collect(Collectors.toList());
	System.out.println(list1);
	//(2) ����Ա������Щ��ͬ�ĳ��й�������
	List<String> list2=transactions.stream().map(t-> t.getTrader()).map(t->t.getCity()).distinct().collect(Collectors.toList());
	System.out.println(list2);
//	(3) �������������ڽ��ŵĽ���Ա��������������
	List<Trader> list3=transactions.stream().map(t->t.getTrader()).filter(t->"Cambridge".equals(t.getCity())).distinct().sorted(Comparator.comparing(Trader::getName)).collect(Collectors.toList());
	System.out.println(list3);
//	(4) �������н���Ա�������ַ���������ĸ˳������
	List<String> list4=transactions.stream().map(t-> t.getTrader()).sorted(Comparator.comparing(Trader::getName)).map(t->t.getName()).distinct().collect(Collectors.toList());
	System.out.println(list4);
//	(5) ��û�н���Ա�������������ģ�
	System.out.println(transactions.stream().map(t->t.getTrader()).anyMatch(t->"Milan".equals(t.getCity())));
//	(6) ��ӡ�����ڽ��ŵĽ���Ա�����н��׶
	transactions.stream().filter(t->"Cambridge".equals(t.getTrader().getCity())).map(Transaction::getValue).forEach(System.out::println);
//	(7) ���н����У���ߵĽ��׶��Ƕ��٣�
	transactions.stream().max(Comparator.comparing(Transaction::getValue)).map(Transaction::getValue).ifPresent(System.out::println);
//	(8) �ҵ����׶���С�Ľ��ס�
	transactions.stream().min(Comparator.comparing(Transaction::getValue)).ifPresent(System.out::println);
}
}
