 * Este programa implementa um buscador de CEP que consulta uma API web para obter informações detalhadas sobre um endereço.
 * Utilizando o framework SAX para leitura de arquivos XML, o programa se conecta à API e busca informações como logradouro, bairro,
 * cidade e estado, dado um CEP fornecido pelo usuário.

 * Requisitos:
 * - Biblioteca 'SAXReader' para ler o XML retornado pela API.
 * - Uma API de consulta de CEP válida (ex: República Virtual).
 
 * Exemplo de uso:
 * 1 O usuário insere o CEP desejado.
 * 2 O programa consulta a API, faz o parsing do XML e preenche os campos com as informações do endereço.
