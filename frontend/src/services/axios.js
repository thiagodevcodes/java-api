import { toast } from "react-toastify";
import axios from "axios";

export const fetchData = async (limit, currentPage, path) => {
    try {
        const response = await axios.get(`http://localhost:8080/api/orcamento/${path}?size=${limit}&page=${currentPage}`);
        return response.data
    } catch (error) {
        console.error('Erro ao buscar dados:', error);
    }
};   

export const fetchDataAll = async (path) => {
    try {
        if(!path) return
        const response = await axios.get(`http://localhost:8080/api/orcamento/${path}/all`);
        return response.data
    } catch (error) {
        console.error('Erro ao buscar dados:', error);
    }
};   

export const fetchDataById = async (id, path) => {
    try {
        const response = await axios.get(`http://localhost:8080/api/orcamento/${path}/${id}`);
        return response.data
    } catch (error) {
        console.error('Erro ao buscar dados:', error);
        toast.error("Erro ao buscar dado!")
    }
};

export const handleCreate = async(data, path, router, controlModal) => {
    try {       
        console.log(data)
        await axios
            .post(`http://localhost:8080/api/orcamento/${path}`, data)
            .then(() => {               
                setTimeout(() => {
                    router.reload();
                }, 3000)    
                controlModal("post", false)
                toast.success('Cadastrado com sucesso!')
                //console.log(response);
          })
          .catch((error) => {
            console.log(error);
            toast.error('Dados inválidos, tente novamente!')
          });
        } catch (error) {
        //console.error(error);
        toast.error('Ocorreu um erro ao cadastrar!')
    }
}


export const handleUpdate = async (id, data, path, router, controlModal) => {
    try {
        await axios.put(`http://localhost:8080/api/orcamento/${path}/${id}`, data)
        .then((response) => {
            setTimeout(() => {
                router.reload();
            }, 3000)  
            controlModal("update", false);
            toast.success("Atualizado com sucesso!")
            console.log(response);
        }).catch((error) => {
            console.log(error);
            toast.error('Dados inválidos, tente novamente!')
        })
    } catch (error) {
        console.error(error);
        toast.error("Ocorreu um erro ao atualizar!")
    }
}

export const handleDelete = async(id, path, router) => {
    try {
        await axios.delete(`http://localhost:8080/api/orcamento/${path}/${id}`);
        router.reload();
    } catch (error) {
        console.error('Erro ao buscar dados:', error);
        toast.error("Erro ao deletar a ação!");
    }
}

export const getAllData = async() => {
    const paths = [
        "acao",
        "elemento-despesa",
        "programa",
        "unidade",
        "grupo-despesa",
        "unidade-orcamentaria",
        "solicitante",
        "tipo-lancamento",
        "tipo-transacao",
        "objetivo-estrategico",
        "fonte-recurso",
        "modalidade-aplicacao",
        "lancamento"
    ]

    try {
        const dataArray = await Promise.all(
          paths.map(async (path) => {
            const data = await fetchDataAll(path);
            // console.log(`Data from ${path}:`, data); // Adicione esta linha
            return data;
          })
        );
        return dataArray;
      } catch (error) {
        console.error('Erro ao buscar dados:', error);
    }  
}


