# ARES

Esse Sistema funciona como servico de CEP e mediacao de dados, recomendado para mediar contexto. Baseado em Spring Integration, Espercep e Kafka. Esta ai pra vc usar, fiz isso para meus amigos e simpatizantes do meu trabalho. Criticas nao sao bem vindas, ja sei dos defeitos e qualidades do meu trabalho que ja foi inclusive avaliado por gente do mundo inteiro, entao me poupe de chateacao, porem se quiser, reportar algum bug, saber mais detlhes e tiver interesse, estou disponivel no email: hbmd@cin.ufpe.br. 

Pra rodar esse negocio faz o seguinte: 

1 - Instala o kafka na configuracao padrao.
2 - Importa o projeto ServiceMiddlewareCEPKafka no eclipse
3 - Consfigura kafka.properties na pasta resources, se liga nos nomes das filas que tu vai dar
4 - Importa o projeto ProducerCEPKafka e executa na sequencia:

a) MainProducerCEP5.java add event
b) MainProducerCEP5.java add rule
c) MainProducerCEP2.java edit rule - nao esquece de mudar o id em: "\"value\" : \"cf943226-be26-4e80-a9ad-516554ddcfbe\"
d) MainProducerCEP54.java delete event nao esquece de mudar o id em: "\"value\" : \"cf943226-be26-4e80-a9ad-516554ddcfbe\"
