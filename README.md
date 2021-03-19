# Microservices
Made by Ivan Myrotiuk

Microservices
Monolite vs microservices
when we are using microservices architecture we divede our app on small services and instead of redeploying whole app 
we need just redeploy one particular service 
Also we are able to scale our application and have deployed several instances at the same time and manage all calls with load balancers

In monolith we have got specific problem and solution. For example how this shoping card service will be called from shoping catalog service
it is specific problem in monolith but when we are seperate our monolith to microservices this problem becomes general one 
as th ere are bunch of technologes which is helping to sove thoose common problems that relate to comunications between services.


Fault tolerance refers to the ability of a system (computer, network, cloud cluster, etc.) 
to continue operating without interruption when one or more of its components fail. ... 
Fault-tolerant systems use backup components that automatically take the place of failed components,
 ensuring no loss of service
the ability to deal with something unpleasant or annoying, or to continue existing despite bad or difficult conditions:

resilience
the quality of being able to return quickly to a previous good condition after problems

How do we make our microservices resilient:
1. What to do if one of them will go down. We may run several of service instances and requests will be handled by loadblancer
 and application will continue operate

When our services depend on third parties services and request from our services to those third parties goes slow we need to 
set up timeout in order to reduce number of threads and to make our whole application faster. Timeout will help partly because 
income request faster then 3 sec for timeout. If frequency for income request is slow it will help but if not just partially

Circuit breaker pattern
-Detect something is wrong
-Take temporary steps to avoid the situation getting worse(stop sending request to the service)
-Deactivate the "problem" component so that doesn't affeect downstream components
for some time after some period of time circuit resume and it again try to send the request 

Circuit breaker its basic function is to interupt current flow after a fault is detected.
Can be reset (either manually or automatically) to resume normal operation.
Circuit breaker is importent and may be applied in all services which makes calls to another one services
In order to release resources. Especially when one service makes calls to several another one services.
----------------
When does the circuit trip?
- Last n request to consider for the decision
- How many of those should fail?
- Timeout duration

When does the circuit should come back?
-How long after a circuit trip to try again?(How long to wait(sleep window):10s)
----------------

We need a fallback
we need return something to the client who send the request
-Throw an error (bad solution)
-Return a fallback "default" response
-Save previous responses (cache) and use that when possible

Hystrix is responsible for circuit break


Configuration
We need hold config file separated from code without hardcoded them 
-Database connections
-credentials
-Feature flags
-Business logic configuration params
-Spring Boot configuration 
Without deploying another version of microservices just configure it with externalize system
Goals
-Externalized
-Environment specific(dev,test,QA)
-Consistent(all different micro services should look at the same one config, talk with one config server which is looking to git repo in order to get specific config)
config repo is a source for our config server, we do not need to redeploy just push our configuration to that git repo.
-Version history
-Real-time management (We want to make a change and have affect on our microservices when all our microservices are runing)









