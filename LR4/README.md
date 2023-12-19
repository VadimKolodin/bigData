# Лабораторная работа 4

### Зоопарк
Создаем узел / zoo с помощью zkCli.sh:

Запускаем первого клиента:


И второго:



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

Результат работы с 3мя клиентами:

(У клиентов в качестве имени случайные номера для простоты запуска)

Клиент 1:

Клиент 4:

Клиент 5:

Координатор:

