# Network Time Protocol
> NTP Server for Clock Sync. in Distributed System and Operative System Environment

NTP_Server is a simple educational project, in witch i build a Network Time Protocol System in Java after i learned Clock Synchronization chapter about Distributed Systems.
<br>
NTP Servers are very useful in a Distributed Environment due to Quarz Clock use in computers, mobile devices and server in a data center. Quarz Clocks are made of earth material, so are very sensitive to temperature changes, those can decrease or increase the frequency of the clock, for that reason is crucial for all nodes in a Distributed Systems to have a correct time estimation. <br>
When we say time we don't mean the time assumption in the System Model area, istead we mean time stamp, that are fundamentals in a Distribued System. <br>
<br>
The best solution is to provide a wide number of NTP servers that give their time stamp (based on GPS clock approximated according to UTC). Then the server time stamp must be sent back to the request node. Once the response has arrived the request node must approximate the time stamp to synchronized it with the server one. <br>
The approximation that a node can do are based on the following values:
* Network Delay: is important to understand how the network latency effects the time elapsed. By this formula we can estimate it (T4 - T1) - (T3 - T2).
* Server time stamp before sending the reply: T3 + Network Latency / 2.
* Clock Skrew: T4 - (T3 + Network Latency / 2).



