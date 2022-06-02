# Mockito Extension

`@Mock`, `@InjectMocks` 를 사용하려면 `@ExtendWith(MockitoExtension.class)` 를 해줘야 한다.<br>

테스트 코드는, 실제로 업무로 사용했었던 RabbitMQ 푸시 로직의 테스트 로직을 예제로 가져왔다. 실무의 비즈니스 로직이 포함되지 않았고, 여러가지 계산 로직에 대한 내용이 포함되지 않았기에 저작권에는 문제가 되는 부분은 없다. 그래서 정리를 하게 됐다.<br>

<br>

# 테스트 코드 예시

**SupplyFieldEntryAsyncTest.java**

```java
@ExtendWith(MockitoExtension.class)
public class SupplyFieldEntryAsyncTest {
	Logger logger = LoggerFactory.getLogger(SupplyFieldEntryAsyncTest.class);

	@Mock
	RabbitTemplate mockRabbitTemplate;

	@InjectMocks
	PricePushConsumer pricePushConsumer;

	@Test
	public void TEST_SEND_RABBITTEMPLATE(){
		List<Price> priceList = List.of(new Price(...), new Price(...), new Price(...));
		pricePushConsumer.supplyAsyncSend(priceList);

		Mockito.verify(mockRabbitTemplate, Mockito.times(1))
			.convertAndSend(anyString(), anyString(), any(Price.class));
	}
}
```

