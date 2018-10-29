package java8.chapter5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.TimeZone;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

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
	
	//(1) 找出2011年发生的所有交易，并按交易额排序（从低到高）
	List<Transaction> list1=transactions.stream().filter(t -> t.getYear()==2011)
			.sorted(Comparator.comparing(Transaction::getValue))
			.collect(Collectors.toList());
	System.out.println(list1);
	//(2) 交易员都在哪些不同的城市工作过？
	List<String> list2=transactions.stream().map(t-> t.getTrader()).map(t->t.getCity()).distinct().collect(Collectors.toList());
	System.out.println(list2);
//	(3) 查找所有来自于剑桥的交易员，并按姓名排序。
	List<Trader> list3=transactions.stream().map(t->t.getTrader()).filter(t->"Cambridge".equals(t.getCity())).distinct().sorted(Comparator.comparing(Trader::getName)).collect(Collectors.toList());
	System.out.println(list3);
//	(4) 返回所有交易员的姓名字符串，按字母顺序排序。
	List<String> list4=transactions.stream().map(t-> t.getTrader()).sorted(Comparator.comparing(Trader::getName)).map(t->t.getName()).distinct().collect(Collectors.toList());
	System.out.println(list4);
//	(5) 有没有交易员是在米兰工作的？
	System.out.println(transactions.stream().map(t->t.getTrader()).anyMatch(t->"Milan".equals(t.getCity())));
//	(6) 打印生活在剑桥的交易员的所有交易额。
	transactions.stream().filter(t->"Cambridge".equals(t.getTrader().getCity())).map(Transaction::getValue).forEach(System.out::println);
//	(7) 所有交易中，最高的交易额是多少？
	transactions.stream().max(Comparator.comparing(Transaction::getValue)).map(Transaction::getValue).ifPresent(System.out::println);
//	(8) 找到交易额最小的交易。
	transactions.stream().min(Comparator.comparing(Transaction::getValue)).ifPresent(System.out::println);
	transactions.stream().collect(minBy(Comparator.comparingInt(Transaction::getValue))).ifPresent(System.out::println);
	//按照年份分组
	Object[] transactionsByCurrencies =
			transactions.stream().toArray(size->new Object[size]);
	System.out.println(transactionsByCurrencies);
	//一次性获取最大值，最小值，平均值
	IntSummaryStatistics iss=transactions.stream().collect(summarizingInt(Transaction::getValue));
	System.out.println(iss);
	
	int totalCalories = transactions.stream().collect(reducing(0,
			Transaction::getValue,
			Integer::sum));
	
	//按照交易的年份和人员分类
	System.out.println(transactions.stream().collect(Collectors.groupingBy(Transaction::getYear,Collectors.groupingBy(Transaction::getTrader))));
	Properties props = new Properties();
	Optional<String> os=Optional.ofNullable(props.getProperty(""));
	
	ZoneId zoneId = TimeZone.getDefault().toZoneId();
	Instant instant = Instant.now();
	ZonedDateTime zdt3 = instant.atZone(zoneId);
	System.out.println(zdt3);
	LocalDateTime dateTime = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);
	ZoneOffset offset=ZoneOffset.ofHours(-1);
	Instant instantFromDateTime = dateTime.toInstant(offset);
	System.out.println(instantFromDateTime);
	LocalDateTime timeFromInstant = LocalDateTime.ofInstant(instant, zoneId);
	System.out.println(timeFromInstant);
	System.out.println(Instant.now());
	Thread.yield();
	
}


public Future<Double> getPriceAsync(String product) {
CompletableFuture<Double> futurePrice = new CompletableFuture<>();
new Thread( () -> {
double price = 1;
futurePrice.complete(price);
}).start();
return futurePrice;
}
 class ProductFactory {
public Trader createProduct(String name){
	Supplier<Trader> loanSupplier = Trader::new;
switch(name){
case "loan": return loanSupplier.get() ;
case "stock": return new Trader();
case "bond": return new Trader();
default: throw new RuntimeException("No such product " + name);
}
}
}
}
