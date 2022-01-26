1) Plik ze skryptem do toworzenia bazy danych znajduje się w:

Credit/src/main/resources/database

2) Usługi: 

- post /credit/create/ 

przyjmuje:

{
"creditName": "hipoteczny",
"firstName": "Jan",
"lastName": "Kowalski",
"pesel": "89062706786",
"value": 3000
}

zwraca: 

{
"creditId": 2
}

w przypadku błędnych danych wejsciowych (nieprawdiłowy pesel, ujemna wartosć kredytu, brak nazwy kredytu) zwraca:

{
"creditId": null
}

- get /credit/get

nic nie przyjmuje. 

zwraca:

{
"creditCustomerAggregates": [
{
"firstName": "ala",
"lastName": "arek",
"pesel": "2551555488",
"creditName": "aaa",
"value": 1000,
"creditId": 1,
"customerId": 11
},
{
"firstName": "ala",
"lastName": "arek",
"pesel": "25511144866",
"creditName": "bbbb",
"value": 1500,
"creditId": 3,
"customerId": 25
},
{
"firstName": "ala",
"lastName": "arek",
"pesel": "985444788123",
"creditName": "cccc",
"value": 2500,
"creditId": 4,
"customerId": 29
}
]
}

3. Projekt posiada swaggera. Pod poniższym adresem można zobaczyć wszytskie endpointy:

/swagger-ui/
