## Setup

Docker environment

`cd docker` from project folder

`docker-compose up -d`

`docker-compose down`

Build project

`mvn clean install`



## Summary
The plane starts from random position at ground level with zero speed.
On the Scheduler click plane generates "real telemetry" and calculates updated `targetPoint` where it should adjust trajectory.
Saves passedPoint into Flight and stores updated points in db.

Самолет вычисляет каждую милисекунду сколько он пролетел с текущими параметрами полета и сохраняет в переменную последнего местонаходжения.
Планировщик раз в секунду берет с этомй переменно значение и сохраняет в базу.
При старте полета есть исходная точка. Она же становится базовой для алгоритма вычисления поправки направления полета.
После расчета эта точка заменяется обновленной.
Алгоритм считает угловое отклонение реалього вектора за милисекунду от необходимого вектора до следующего `wayPoint`.
