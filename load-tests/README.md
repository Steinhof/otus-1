# Индексы (часть 2)

* папка composite-index - запросы после применения индекса
* папка no-index - запросы без индекса

Пример sql запроса: ``select * from user where first_name like 'Valeria%' and last_name like 'Bechtelar%'``

Composite индекс ``create index name_index on user (first_name, last_name);``, отрабатывает эффективнее, так как поиск
осуществляется по двум колонкам одновременно

Explain: ``-> Index range scan on user using name_index over ('Valeria\0\' <= first_name <= 'Valeria￿' AND 'Bechtelar\0' <= last_name <= 'Bechtelar'), with index condition: ((`user`.first_name like 'Valeria%') and (`user`.last_name like 'Bechtelar%'))  (cost=69.28 rows=127) (actual time=0.034..0.096 rows=1 loops=1)``