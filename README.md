# ARES

Esse Sistema funciona como servico de CEP e mediacao de dados, recomendado para mediar contexto. Baseado em Spring Integration, Espercep e Kafka. Esta ai pra vc usar, fiz isso para meus amigos e simpatizantes do meu trabalho. Criticas nao sao bem vindas, ja sei dos defeitos e qualidades do meu trabalho que ja foi inclusive avaliado por gente do mundo inteiro. Entao me poupe de chateacao, porem se quiser reportar algum bug, saber mais detlhes e tiver interesse em melhorias, estou disponivel no email: hbmd@cin.ufpe.br. 

Pra rodar esse negocio faz o seguinte: 

1 - Instalar o kafka na configuracao padrao.
2 - Importar o projeto ServiceMiddlewareCEPKafka no eclipse
3 - Consfigurar kafka.properties na pasta resources, se liga nos nomes das filas que tu vai dar
4 - Importar o projeto ProducerCEPKafka e executa na sequencia:

a) MainProducerCEP5.java add event

b) MainProducerCEP.java add rule

c) MainProducerCEP2.java edit rule - nao esquece de mudar o id em: "\"value\" : \"cf943226-be26-4e80-a9ad-516554ddcfbe\"

d) MainProducerCEP54.java delete event nao esquece de mudar o id em: "\"value\" : \"cf943226-be26-4e80-a9ad-516554ddcfbe\"

5 - Importar o projeto ProducerCEPKafka no eclipse executa a classe MainProducerKafka

6 - Importar o projeto ConsumerKafka no eclipse executa a classe MainProducerKafka

Obs.: se mudar o nome das filas vai ter quer configurar os nomes em applciationContext.xml dos projetos de exemplo.

depois de tudo somente ver o streming de dados nas filas no cafka.
