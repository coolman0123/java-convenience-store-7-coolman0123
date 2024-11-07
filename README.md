# java-convenience-store-precourse

# 💵 로또

사용자가 구입할 물건과 개수를 입력받고, 영수증을 출력한다.

## 1. 기능 요구 사항

구매자의 할인 혜택과 재고 상황을 고려하여 최종 결제 금액을 계산하고 안내하는 결제 시스템을 구현한다.

- 사용자가 입력한 상품의 가격과 수량을 기반으로 최종 결제 금액을 계산한다.
    - 총구매액은 상품별 가격과 수량을 곱하여 계산하며, 프로모션 및 멤버십 할인 정책을 반영하여 최종 결제 금액을 산출한다.
- 구매 내역과 산출한 금액 정보를 영수증으로 출력한다.
- 영수증 출력 후 추가 구매를 진행할지 또는 종료할지를 선택할 수 있다.
- 사용자가 잘못된 값을 입력할 경우 `IllegalArgumentException`를 발생시키고, "[ERROR]"로 시작하는 에러 메시지를 출력 후 그 부분부터 입력을 다시 받는다.
    - `Exception`이 아닌 `IllegalArgumentException`, `IllegalStateException` 등과 같은 명확한 유형을 처리한다.

### **재고 관리**

- 각 상품의 재고 수량을 고려하여 결제 가능 여부를 확인한다.
- 고객이 상품을 구매할 때마다, 결제된 수량만큼 해당 상품의 재고에서 차감하여 수량을 관리한다.
- 재고를 차감함으로써 시스템은 최신 재고 상태를 유지하며, 다음 고객이 구매할 때 정확한 재고 정보를 제공한다.

### **프로모션 할인**

- 오늘 날짜가 프로모션 기간 내에 포함된 경우에만 할인을 적용한다.
- 프로모션은 N개 구매 시 1개 무료 증정(Buy N Get 1 Free)의 형태로 진행된다.
- 1+1 또는 2+1 프로모션이 각각 지정된 상품에 적용되며, 동일 상품에 여러 프로모션이 적용되지 않는다.
- 프로모션 혜택은 프로모션 재고 내에서만 적용할 수 있다.
- 프로모션 기간 중이라면 프로모션 재고를 우선적으로 차감하며, 프로모션 재고가 부족할 경우에는 일반 재고를 사용한다.
- 프로모션 적용이 가능한 상품에 대해 고객이 해당 수량보다 적게 가져온 경우, 필요한 수량을 추가로 가져오면 혜택을 받을 수 있음을 안내한다.
- 프로모션 재고가 부족하여 일부 수량을 프로모션 혜택 없이 결제해야 하는 경우, 일부 수량에 대해 정가로 결제하게 됨을 안내한다.
- 
## 2. 기능 목록

1. `"안녕하세요. W편의점입니다.
   현재 보유하고 있는 상품입니다."`를 출력하는 기능
2. products.md에 있는 상품 정보를 출력하는 기능
3. `"구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])"`를 출력하는 기능
4. `"(예: [사이다-2],[감자칩-1])"`와 같이 사용자에게 구매 상품과 개수를 입력받는 기능
   - `4.1 구매상품과 개수 입력에 대한 예외` 참고
5. 프로모션 적용이 가능한 상품에 대해 고객이 해당 수량보다 적게 가져온 경우, 그 수량만큼 추가 여부를 입력받는다.
   - Y: 증정 받을 수 있는 상품을 추가한다. 
   - N: 증정 받을 수 있는 상품을 추가하지 않는다.
6. 프로모션 재고가 부족하여 일부 수량을 프로모션 혜택 없이 결제해야 하는 경우, 일부 수량에 대해 정가로 결제할지 여부를 입력받는다.
   - Y: 일부 수량에 대해 정가로 결제한다.
   - N: 정가로 결제해야하는 수량만큼 제외한 후 결제를 진행한다.
7. 멤버십 할인 적용 여부를 확인하기 위해 안내 문구를 출력한다.
   - Y: 멤버십 할인을 적용한다.
   - N: 멤버십 할인을 적용하지 않는다.
8. 구매 상품 내역, 증정 상품 내역, 금액 정보를 출력한다.
9. 추가 구매 여부를 확인하기 위해 안내 문구를 출력한다.
   - Y: 재고가 업데이트된 상품 목록을 확인 후 추가로 구매를 진행한다.
   - N: 구매를 종료한다.

- `4.2 (Y/N)로 구분된 사용자의 입력에 대한 예외` 참고


### 사용자

```
- 상품명과 상품 개수를 입력하고, 추가적으로 필요한 입력에 대해서 수행하여 영수증을 출력 받는다.
```

### 상품 문서 등록기

```
- products.md 파일을 읽어 들여 상품 데이터를 로드한다.
- 일반 상품과 프로모션 상품을 구분하여 적절한 객체로 생성한다.
- 로드된 상품 정보를 상품 관리 시스템에 등록한다.

```

### 프로모션 문서 등록기

```

- promotions.md 파일을 읽어 들여 프로모션 데이터를 로드한다.
- 각 프로모션의 세부 정보(이름, 구매 수량, 증정 수량, 시작일, 종료일)를 파싱한다.
- 로드된 프로모션 정보를 프로모션 관리 시스템에 등록한다.

```

### 상품 관리기

```
- 일반 상품과 프로모션 상품 클래스를 구현한다.
- 각 상품의 기본 정보(이름, 가격, 재고)를 관리한다.
- 상품별 가격 계산 로직을 구현한다.

```

### 프로모션 관리기

```
- 프로모션 정보(이름, 적용 조건, 할인 방식)를 관리한다.
- 현재 날짜를 기준으로 적용 가능한 프로모션을 필터링한다.
- 프로모션 적용 가능 여부를 확인하고 할인을 계산한다.

```


### 장바구니

```
- 사용자가 선택한 상품과 수량을 관리한다.
- 장바구니 내 상품의 총 금액을 계산한다.
- 프로모션 및 멤버십 할인을 적용하여 최종 결제 금액을 산출한다.

```

### 재고 관리기

```
- 각 상품의 현재 재고 수량을 추적한다.
- 구매 시 재고를 감소시키고, 재고 부족 시 알림을 제공한다.
- 프로모션 상품의 경우 일반 재고와 프로모션 재고를 별도로 관리한다.

```

### 영수증 생성기

```
- 구매 상품 내역, 수량, 가격을 정리한다.
- 적용된 프로모션 및 할인 내역을 포함한다.
- 최종 결제 금액을 계산하고 포맷에 맞춰 영수증을 생성한다.
```


## 3. 개인 목표

- 객체를 객체 답게 활용한다.
- README.md를 살아있는 문서로 유지한다.
- 프로그래밍 요구 사항을 지킨다.
  - indent depth 3미만
  - 3항 연산자 사용하지 않는다.
  - else, switch/case 사용하지 않는다.
  - Java Enum 활용
  - 메서드 길이 10라인 이하로 구현 -> main() 포함
  - 입출력 클래스 별도 구현
    
- TDD 활용
  - 작은 단위 테스트 코드를 활용한다.


## 4. 예외처리

### 4.1 구매상품과 개수 입력에 대한 예외

- 구매할 상품과 수량 형식이 올바르지 않은 경우: [ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.
- NULL 혹은 빈값을 입력했을 경우: [ERROR] 값을 입력해됩니다. 다시 입력해주세요.
- 구매 수량이 재고 수량을 초과한 경우: [ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.
- 존재하지 않는 상품을 입력한 경우: [ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요.
- 기타 잘못된 입력의 경우: [ERROR] 잘못된 입력입니다. 다시 입력해 주세요.


### 4.2 (Y/N)로 구분된 사용자의 입력에 대한 예외

- 숫자가 들어온 경우
- NULL 혹은 빈값을 입력했을 경우
- 문자를 입력한 경우




## 추가로 발생할 수 있는 문제 상황?

- 상품명에 영어나 숫자가 들어갈 수 있나?
- (Y/N) 입력 중 소문자로 입력했을 때?


## 고려할 것

비고

README.md_VER_1.0