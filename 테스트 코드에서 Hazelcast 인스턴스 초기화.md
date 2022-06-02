# 테스트 코드에서 Hazelcast 인스턴스 초기화

실시간 데이터 애플리케이션 개발시 인메모리 캐시로 Hazelcast 를 사용했다. redis 를 사용할 수도 있겠지만, 개발 초기 인프라 구성 및 개발 기한 독촉(내일까지 하세요 등등 생각 없이 막 던지는 오더...)으로 인해 Hazelcast를 선택했는데, 나쁘지 않았던 선택이었다는 생각이 들었었다.<br>

서버 내에 위치하고 있기에, 응답지연 등의 이슈도 크지 않았고, 이종의 기기간 통신으로 인해 환경의 차이에서 오는 장애대응의 어려움 등의 이슈도 적어서 우연하게 선택했던 이 선택이 좋은 선택이었구나 하고 생각했었다.<br>

<br>

테스트 코드에 대한 자세한 설명은 일주일 내로 추가해야겠다. 오늘 무지 바쁘다... 와...<br>

<br>

```java
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

// ...

@ExtendWith(MockitoExtension.class)
public class SupplyFieldEntryAsyncTest{
	Logger logger = LoggerFactory.getLogger(SupplyFieldEntryAsyncTest.class);

	private Config config = new Config();

	@BeforeEach
	public void testLocalInit(){
		config.setInstanceName("ooooo-data-hazelcast");
		// 테스트 코드 내에서는 아래의 부분이 꼭 있어야 한다. 
		// hzInstance 를 클래스 내에서 아무리 사용하는 로직이 없다고 하더라도, 아래 로직으로 Hazelcast를 구동시켜두어야 한다.
		hzInstance = Hazelcast.getOrCreateHazelcastInstance(config);
	}

	@Test
	@DisplayName("SupplyAsync 테스트 ")
	public void TEST_SUPPLY_FIELD_ENTRY_ASYNC(){
		List<FieldEntryDto> fList = newFieldEntryList();
		RawDataBuffer spyBuffer = Mockito.spy(new RawDataBuffer(config, mockRabbitTemplate));

		spyBuffer.supplyFieldEntryAsync(fList);

		Mockito.verify(mockRabbitTemplate, Mockito.times(fList.size()))
			.convertAndSend(anyString(), anyString(), any(FieldEntryDto.class));
	}

	public List<FieldEntryDto> newFieldEntryList(){
		FieldEntryDto f1 = FieldEntryDto.builder()
			.ticker(SPY)
			.fieldMap(new HashMap<>())
			.symbolDto(new SymbolDto())
			.build();

		FieldEntryDto f2 = FieldEntryDto.builder()
			.ticker(MSFT)
			.fieldMap(new HashMap<>())
			.symbolDto(new SymbolDto())
			.build();

		FieldEntryDto f3 = FieldEntryDto.builder()
			.ticker(NKE)
			.fieldMap(new HashMap<>())
			.symbolDto(new SymbolDto())
			.build();

		return List.of(f1, f2, f3);
	}
}
```

