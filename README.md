# Java REST Client with Multi-Threading support

## Requirements

To run the this project you need the following tools:
<img align="center" alt="GIT" height="25" width="35" src="https://raw.githubusercontent.com/devicons/devicon/master/icons/git/git-original.svg" style="max-width:100%;">[Git](https://git-scm.com)</img>

<img align="center" alt="NodeJS" height="25" width="35" src="https://raw.githubusercontent.com/devicons/devicon/master/icons/nodejs/nodejs-original.svg" style="max-width:100%;">[Node.js](https://nodejs.org/en/)</img>

<img align="center" alt="NodeJS" height="25" width="35" src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" style="max-width:100%;">[Java](https://www.java.com/en/download/)</img>

<img align="center" alt="NodeJS" height="25" width="35" src="https://raw.githubusercontent.com/devicons/devicon/master/icons/vscode/vscode-original.svg" style="max-width:100%;">[VS Code](https://www.java.com/en/download/)</img>

## Quick Start

### Server
To start the client, first install all the dependencies using the following command,
```
npm install
```
After installing the dependencies, you can start the server using the following command,
```
npm start
```

### Client
Open the Java API consumer client in a Java IDE of your choice like Eclipse, Netbeans or VS code.

Install all the maven dependencies and make sure the server is started.

Then, run the MultiThreadMain.java module.

## Intent

Sometimes, API may return only limited results. But we may need to fetch all the results. So we need to implement a intermediate service which will fetch all the results simultaneously using multi-threading and combine all the results. The workaround will improve the original API performance using our proxy service.

## Contributions

This is an Open source project. Everyone is welcome to contribute and it will be much appreciated.