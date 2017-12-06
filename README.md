#Fishbone Introduction


| service| port | desc |
| ------| ------ | ------ |
| head   |   8000  | First App server|
| user   |   8001  |User and Auth Center|
| account |  8002  |Account center|
| pay    |   8003  |Weixin pay|
| settle  |  8004  |Settle System base on spring batch|
| idgen   |  8005  |Global id generator|
| job     |  8006  |Quartz job|
| tail    |  8080  |Management |
| sleuth server |3080 |service chain monitor|
| admin server  |5080| System monitor|
| config server |6080| Config center|
| zuul server |7080 | api gateway|
| eureka server |9080 |discovery |
| lock |jar |distribute lock |

