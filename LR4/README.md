# Лабораторная работа 4

### Зоопарк
Создаем узел / zoo с помощью zkCli.sh:

![image](https://github.com/VadimKolodin/bigData/assets/105828231/1a7d4df0-343a-4c96-938b-0aa35b9a07b3)

Запускаем первого клиента:

![image](https://github.com/VadimKolodin/bigData/assets/105828231/a0ca9267-28d2-4eea-9a09-5a12e7f846bd)
![image](https://github.com/VadimKolodin/bigData/assets/105828231/ef0e72d6-9a5c-487d-9e17-53def1213125)

И второго:
![image](https://github.com/VadimKolodin/bigData/assets/105828231/aeb84def-f3cf-4af8-9aff-d7ea236f739f)


### Философы

Программа реализована по стандартному алгоримту, где каждая вилка - ресурс, для упрощения - мьютекс. 
Синхрониация осуществляется через мьютексы.
Создаем узел:

Результат работы программы с 4мя философами:
2023-12-17T16:40:53.352241500 Philosopher 3 is ready to eat

2023-12-17T16:40:53.352241500 Philosopher 2 is ready to eat

2023-12-17T16:40:53.352241500 Philosopher 0 is ready to eat

2023-12-17T16:40:53.352241500 Philosopher 1 is ready to eat

2023-12-17T16:40:53.373251500 Philosopher 2 got the left fork

2023-12-17T16:40:53.373251500 Philosopher 2 got the right fork

2023-12-17T16:40:53.473810700 Philosopher 1 got the left fork

2023-12-17T16:40:53.574564600 Philosopher 0 got the left fork

2023-12-17T16:40:57.374245500 Philosopher 2 put the right fork

2023-12-17T16:40:57.374245500 Philosopher 2 put the left fork

2023-12-17T16:40:57.374245500 Philosopher 1 got the right fork

2023-12-17T16:40:57.374245500 Philosopher 2 finished eating

2023-12-17T16:40:57.374245500 Philosopher 2 is thinking

2023-12-17T16:40:57.579736100 Philosopher 3 got the left fork

2023-12-17T16:40:58.382341400 Philosopher 1 put the right fork

2023-12-17T16:40:58.382341400 Philosopher 2 is ready to eat

2023-12-17T16:40:58.382341400 Philosopher 1 put the left fork

2023-12-17T16:40:58.382341400 Philosopher 0 got the right fork

2023-12-17T16:40:58.382341400 Philosopher 1 finished eating

2023-12-17T16:40:58.382341400 Philosopher 1 is thinking

2023-12-17T16:40:58.386382200 Philosopher 2 got the left fork

2023-12-17T16:41:00.388526900 Philosopher 0 put the right fork

2023-12-17T16:41:00.388526900 Philosopher 0 put the left fork

2023-12-17T16:41:00.388526900 Philosopher 0 finished eating

2023-12-17T16:41:00.388526900 Philosopher 0 is thinking

2023-12-17T16:41:00.388526900 Philosopher 3 got the right fork

2023-12-17T16:41:01.391214600 Philosopher 0 is ready to eat

2023-12-17T16:41:02.398973 Philosopher 1 is ready to eat

2023-12-17T16:41:03.396213800 Philosopher 0 got the left fork

2023-12-17T16:41:03.396213800 Philosopher 3 put the right fork

2023-12-17T16:41:03.396213800 Philosopher 0 got the right fork

2023-12-17T16:41:03.396213800 Philosopher 3 put the left fork

2023-12-17T16:41:03.396213800 Philosopher 3 finished eating

2023-12-17T16:41:03.396213800 Philosopher 3 is thinking

2023-12-17T16:41:03.396213800 Philosopher 2 got the right fork

2023-12-17T16:41:05.402855900 Philosopher 2 put the right fork

2023-12-17T16:41:05.402855900 Philosopher 2 put the left fork

2023-12-17T16:41:05.402855900 Philosopher 2 finished eating

2023-12-17T16:41:05.402855900 Philosopher 2 is thinking

2023-12-17T16:41:08.399616900 Philosopher 3 is ready to eat

2023-12-17T16:41:08.399616900 Philosopher 1 got the left fork

2023-12-17T16:41:08.399616900 Philosopher 1 got the right fork

2023-12-17T16:41:08.399616900 Philosopher 0 put the right fork

2023-12-17T16:41:08.399616900 Philosopher 0 put the left fork

2023-12-17T16:41:08.399616900 Philosopher 0 finished eating

2023-12-17T16:41:08.399616900 Philosopher 0 is thinking

2023-12-17T16:41:08.403029500 Philosopher 3 got the left fork

2023-12-17T16:41:08.403029500 Philosopher 3 got the right fork

2023-12-17T16:41:10.405281400 Philosopher 3 put the right fork

2023-12-17T16:41:10.405281400 Philosopher 3 put the left fork

2023-12-17T16:41:10.405281400 Philosopher 3 finished eating

2023-12-17T16:41:10.405281400 Philosopher 3 is thinking

2023-12-17T16:41:13.408325500 Philosopher 1 put the right fork

2023-12-17T16:41:13.408325500 Philosopher 1 put the left fork

2023-12-17T16:41:13.408325500 Philosopher 1 finished eating

2023-12-17T16:41:13.408325500 Philosopher 1 is thinking


### Двухфазная транзакция
Выбран следующий алгоритм:
1. Координатор ожидает подключения всех клиентов
2. Клиенты создают дочерние узлы, с данными о решении, тем самым оповещая координатора. 
3. Клиенты ожидают решения координатора
4. Координатор собирает решения, при хотя бы одном решении ROLLBACK транзакция откатывается.
5. Координатор помещает в узлы клиентов финальное решение
6. Узлы клиентов считывают решение, после чего отключаются

Создаем узел / tx с помощью zkCli.sh:

![image](https://github.com/VadimKolodin/bigData/assets/105828231/b5e6e54a-a53e-4e4a-aee2-8df2c5ea95f5)

Результат работы с 3мя клиентами (у клиентов в качестве имени случайные номера для простоты запуска):

Клиент 1:
![image](https://github.com/VadimKolodin/bigData/assets/105828231/9365a603-e8ba-4b5e-af93-0316c4580eed)
![image](https://github.com/VadimKolodin/bigData/assets/105828231/898db201-cf6c-49c7-b22f-be9716fb590f)

Клиент 5:

![image](https://github.com/VadimKolodin/bigData/assets/105828231/30d722cf-c801-4f69-9554-fce920e2c173)
![image](https://github.com/VadimKolodin/bigData/assets/105828231/d3e4e42f-ddd9-4549-a2f2-c6aacb4ec249)

Клиент 4:

![image](https://github.com/VadimKolodin/bigData/assets/105828231/5cb8642b-f163-427a-9826-db5ce7964829)
![image](https://github.com/VadimKolodin/bigData/assets/105828231/f8e707f0-0482-44f7-9e3b-4b563aba90f4)

Координатор:

![image](https://github.com/VadimKolodin/bigData/assets/105828231/28192e13-06a6-4a07-a88f-75b4b8e8d274)
![image](https://github.com/VadimKolodin/bigData/assets/105828231/e4833ce5-fa51-404b-962b-31b9e1ab8d93)
![image](https://github.com/VadimKolodin/bigData/assets/105828231/0686d63e-6ed7-40b8-953e-d7feb876f41f)
