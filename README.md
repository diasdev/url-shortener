# URL Shortener demo

## Execução
docker run -p 8080:8080 rddev/url-shortener-demo

## Utilização

### Obter uma URL abreviada
POST request para http://localhost/shorten/
body: URL original

### Acessar URL original
Acessar URL retornada do POST

## Implementação
A persistência dos código gerados e URLs originais é feita em duas fases:
1) em memória.
2) em disco.

O objetivo é "plugar" um cliente de um bando de dados em memória e resiliente (Redis). 
Nesta implementação foi usada uma versão local em memória (com ConcurrentHashMap).

O código de acesso à URL original é gerado com base em um hash SHA3, começando a contar a partir de 7 caracteres.
Em caso de colisão com uma URL já salva, incrementa 1 no tamanho e asim sucessivamente até chegar a um código único (SHA3 ainda não teve colisões simuladas).
As URLs abreviadas são geradas usando o schema base (ex.: http://mydomain.com) e o código hash (/abc1233).

### Escalabilidade
Compartilhando o mesmo data store, a aplicação pode ser escalada horizontalmente.
O uso da persistência em disco pode ser dispensado dependendo do nível de tolerância a falhas desejado. 
Pode-se utilizar a própria persistência do Redis. Isso garante mais performance e Redis já tem operações atômicas para garantir consistência num ambiente distribuído.
