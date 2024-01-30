INSERT INTO category(name, description)
VALUES ('Swift', '문법'),
       ('SwiftUI', '스위프트유아이'),
       ('UIKit', '유아이킷');

INSERT INTO exam(category_id, name)
VALUES (1, 'ContentView'),
       (1, '@State'),
       (2, 'UIViewController'),
       (2, 'IBOutlet'),
       (3, 'Struct'),
       (3, 'guard');

INSERT INTO solution(explanation)
VALUES ('ContentView'),
       ('ContentView'),
       ('@State'),
       ('@State'),
       ('UIViewController'),
       ('UIViewController'),
       ('IBOutlet'),
       ('IBOutlet'),
       ('Struct'),
       ('Struct'),
       ('guard'),
       ('guard');

INSERT INTO ox_solution(solution_id, answer)
VALUES (1, true),
       (2, false),
       (3, true),
       (4, false),
       (5, true),
       (6, false),
       (7, true),
       (8, false),
       (9, true),
       (10, false),
       (11, true),
       (12, false);

INSERT INTO question(exam_id, solution_id, paragraph)
VALUES (1, 1, 'SwiftUI에서 사용되는 기본 뷰 구조체'),
       (1, 2, 'SwiftUI의 주요 기능 중 하나가 아니다.'),
       (2, 3, 'SwiftUI에서 상태를 관리하고 업데이트할 때 사용되는 속성 래퍼'),
       (2, 4, 'Swift에서만 사용되는 특별한 문법이다.'),
       (3, 5, 'UIKit에서 사용되는 뷰 컨트롤러 클래스'),
       (3, 6, 'UIViewController는 SwiftUI에서만 사용되고 UIKit에서는 사용되지 않는다.'),
       (4, 7, 'Interface Builder에서 인터페이스와 코드를 연결하는 데 사용되는 키워드'),
       (4, 8, 'IBOutlet은 SwiftUI에서만 사용되며 UIKit에서는 사용되지 않는다.'),
       (5, 9, '값 타입을 정의하기 위해 사용되는 키워드'),
       (5, 10, 'Struct는 참조 타입을 나타내며 값 타입이 아니다.'),
       (6, 11, '조건을 검사하고 조건이 충족되지 않으면 빠른 종료를 수행하는 제어문'),
       (6, 12, 'guard는 if문과 동일한 역할을 수행하며 별도의 차이가 없다.');

INSERT INTO users(role)
VALUES ('USER'),
       ('ADMIN');