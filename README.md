# NetworkHelper Library



The Network Helper library provides a set of functions for checking network connectivity in Java applications. It includes functions for getting the network type, network usage, IP address, and checking network availability.

## Installation

You can easily add this library to your project by adding the following dependency to your build.gradle file:

```groovy

implementation 'com.github.LiwaaCoder:NetworkHelper:1.1.1'

```

Usage

To use the Network Helper library in your Java project, simply create an instance of the NetworkConnectivity class and call the relevant functions. Here's an example:

java

```groovy
NetworkConnectivity networkConnectivity = new NetworkConnectivity();
if (networkConnectivity.isNetworkAvailable()) {
    String networkType = networkConnectivity.getNetworkType();
    int usage = networkConnectivity.getNetworkUsage();
    String ipAddress = networkConnectivity.getIpAddress();
    // Do something with the network information
} else {
    // Handle no network connection
}

```

API Reference

Here's a list of the functions provided by the Network Helper library:

    getNetworkType() - This function returns the type of network connection that the device is currently using, such as Wi-Fi, mobile data, or Ethernet.
    getNetworkUsage() - This function returns information about the current network usage, such as the amount of data that has been sent and received.
    getIpAddress() - This function returns the IP address of the device, which is used to identify it on the network.
    isNetworkAvailable() - This function returns a boolean value indicating whether the device is currently connected to a network and is able to access the internet.

Contributing

If you have any suggestions or find any bugs, please feel free to open an issue or submit a pull request. Contributions are always welcome!
