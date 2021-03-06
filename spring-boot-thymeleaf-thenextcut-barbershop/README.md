#  The next cut - Barbershop
Projeto com SpringBoot e Thymeleaf para gerenciamento de Agenda de uma Barbearia. <br>
System for managing salon services. Calendar management as well as the establishment's service agenda will be available.

This article will walk you through building an application called The next cut  Barbershop.

Follow each step to build an app from scratch, or skip to the end get the source for this article. You can also use almost any existing Maven webapp project.

## Pre requisites

* Basic Java knowledge, including an installed version of the JVM and Maven.
* Basic Git knowledge, including an installed version of Git.

### How Does The next cut - Barbershop Work?

When using Webapp Runner you'll launch your application locally and on Heroku with a command like this:
    
    :::term
    $ java -jar thenextcut-barbershop.jar application.war
    INFO: Starting ProtocolHandler ["http-8080"]

The next cut Barbershop will then launch a Tomcat instance with the given war deployed to it. This takes advantage of Tomcat's embedded APIs and is similar to an option that Jetty offers: [Jetty Runner](http://blogs.webtide.com/janb/entry/jetty_runner).

## Run your Application

To build your application simply run:

    :::term
    $ mvn package

And then run your app using the java command:

    :::term
    $ java -jar target/thenextcut-barbershop.jar target/*.war

That's it. Your application should start up on port 8080.

Congratulations! Your web app should now be up and running on Heroku. Open it in your browser with:

## Clone the source

If you want to skip the creation steps you can clone the finished sample (without memcache backed session):

    $ git clone git@github.com:coder-samuel/Thenexcut-API.git


# 1. Passos realizados no Projeto
1. Definição de Requisitos:
Levanta os requisitos mínimos, estuda a viabilidade e define o modelo a ser usado;

2. Documentação do Projeto:
Envolve atividades de concepção, especificação, design da interface, prototipação, design da arquitetura;

3. Implementação e desenvolvimento:
Tradução para uma linguagem de programação das funcionalidades definidas durante as fases anteriores;

4. Cobertura e testes:
Realização de testes no que foi desenvolvido de acordo com os requisitos;

5. Disponilização em produção:
Implantação em produção do produto final;

# 2. Ecossistema
![tecnologias-thenextcut](https://user-images.githubusercontent.com/80654468/140266158-3c3ccb13-52cd-4de4-8c99-28fe7134699a.png)

# 3. Documentação
- **Requisitos funcionais:**
Se trata das funcionalidades que o sistema deve ter.

		1: O sistema deve permitir que o funcionario insira no calendário serviços a serem realizados;
		2: O sistema deve permitir que o funcionario remova do calendário serviços a serem realizados;
		3: O sistema deve possibilitar a consulta de serviços que já foram ou serão realizados;
		4: O sistema deve possibilitar a alteração apenas de serviços que serão realizados;
		5: O sistema possibilitara ao usuario inserir despesas do estabelecimento, como: contas, produtos, serviços, manutenções e gastos imprevistos para seu funcionamento;
		6: O sistema permitira inserir dados dos clientes, como: nome, endereço e contatos;
		7: O sistema permitira alterar os dados dos clientes;
		8: O sistema permitira remover clientes caso seja necessário;
		9: O sistema deverá apresentara a lista de clientes e seus respectivos contatos para consulta;
		10: O sistema apresentara os próximos serviços a serem realizados no estabelecimento, para o dia de referência;
		11: O sistema deverá alertar o funcionario na proximidade de horario para os próximos serviços a serem realizados;
		12: O sistema permitira inserir dados de fornecedores, como: nome, endereço e contatos;
		13: O sistema permitira alterar os dados dos fornecedores;
		14: O sistema permitira remover fornecedores caso seja necessário;
		15: O sistema deverá apresentara a lista de fornecedores e seus respectivos contatos para consulta;
		16: O sistema possibilitara consultar a movimentação de caixa, referente as entradas e saidas;
		17: O sistema possibilitara filtrar a movimentação de caixa por: entrada, tipo de entrada, saida, tipo de saida, dia, mês, ano e data vigente;

- **Requisitos não funcionais:**
Estes tratam de recursos que não são funcionalidades, mas sim características do sistema, como restrições, segurança, confiabilidade, velocidade, validações. entre outros.

		1: Desempenho:
			→ O processamento e tempo de resposta do sistema deverá ser rapido, haja vista que a base do sistema não terá vinculo com a internet da pessoa;
		2: Disponibilidade:
			→ Não haverá janela de manutenção, portanto o sistema não estara inoperante, podendo ser utilizado 24 h por dia;
			→ Todos os dados serão salvos instantaneamente no BD, caso ocorra indisponibilidade ou queda à nivel de hardware ou software;
		3: Segurança:
			→ Não haverá armazenamento de senhas e usuarios de sistema. Sistema será local, sem servidor web;
		4: Compatibilidade:
			→ Disponivel apenas para Sistema Operacional Windows. Será necessario que o SO tenha Browser de preferencia, Banco de Dados e JAVA instalado;
		5: Confiabilidade:
			→ A aplicaçao irá persisitir os dados em Banco de Dados. Será mantido tbm arquivo backup de aplicação, vinculando de preferência com o OneDrive;
			→ O Backup devera ser discutido, podendo ser uma ação schedulada batch, ou por requisição do usuario do sistema;
		6: Padrões:
			→ O sistema será desenvolvido em JAVA e utilizara o Spring Framework, seguindo a estrutura Spring Boot e Spring MVC para uma aplicação Web. 
			→ Utilizara o paradigma Orientada a Objetos para o desenvolvimento backend e também o paradigma Relacional para o Banco de Dados. A integragação entre os paradigmas será realizada com a implementação do Hibernate, através da biblioteca padrão de persistencia de objetos no JAVA, o JPA.
			→ Caso ocorra algum problema na aplicação, que não foi identificado nos testes, este estara disponivel em arquivos de logs do sistema (.txt);

- **Diagrama de Caso de Uso:** Auxilia no levantamento dos requisitos funcionais do sistema, descrevendo um conjunto de funcionalidades do sistema e suas interações com elementos externos.
![casodeuso](https://user-images.githubusercontent.com/80654468/140267111-809e7f67-da26-4ac3-8c60-e88c73d1d951.png)

- **Diagrama de Classes:** É a abstração de um objeto da vida real (vida real que será tratada via software), que agrupa dados (atributos) e procedimentos (operações) relacionados ao seu contexto.

![uml](https://user-images.githubusercontent.com/80654468/140266633-b2de5b4f-07e3-4ce8-ada7-0da28f800c28.png)


- **Diagrama Entidade Relacionamento (DER):** É um modelo conceitual utilizado na Engenharia de Software para descrever os objetos (entidades) envolvidos em um domínio de negócios, com suas características (atributos) e como elas se relacionam entre si (relacionamentos). Em geral, este modelo representa de forma abstrata a estrutura que possuirá o banco de dados da aplicação.

![der](https://user-images.githubusercontent.com/80654468/140266619-143e5df1-15af-4f70-8cd9-af3e58686d52.png)


