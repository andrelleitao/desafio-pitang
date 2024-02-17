/**
 * Contém todas os endpoints da API utilizados pela aplicação.
 */
const ENDPOINT = {
    AUTH: '/api/signin',

    ACESSO_GRUPO: "/acesso/grupos",
    ACESSO_USUARIO: "/acesso/usuarios",
    ACESSO_PERMISSAO: "/acesso/permissoes",

    CLUBE_PLANO: "/clube/planos",

    CADASTRO_FILIAL: "/cadastro/filiais",

    DASHBOARD: "/dashboard",
    CAR: "/api/cars",
    COLABORADOR: "/colaboradores",    
    PRODUTO: "/produtos",

    VENDA: "/vendas",    
    VENDA_MOTIVO: "/vendas/motivos",
    VENDA_PROMOCAO: "/vendas/promocoes",

    FINANCEIRO_CATEGORIA: "/financeiro/categorias",
    FINANCEIRO_FORNECEDOR: "/financeiro/fornecedores",
    FINANCEIRO_CONTA_PAGAR: "/financeiro/contas/pagar",
    FINANCEIRO_CONTA_RECEBER: "/financeiro/contas/receber",
    FINANCEIRO_FORMA_PAGAMENTO: "/financeiro/formas-pagamento",

    RELATORIO_FATURAMENTO: "/relatorio/faturamento", 
    RELATORIO_FLUXO_CAIXA: "/relatorio/fluxoCaixa", 

    FLUXO_CAIXA_CAIXA: "/fluxo-caixa/caixas",
    FLUXO_CAIXA_MOVIMENTACAO: "/fluxo-caixa/caixas/movimentacoes",
};

export default ENDPOINT;
