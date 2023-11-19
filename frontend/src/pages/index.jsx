import Nav from "@/components/Nav"

export default function Home(props) {
    return (
        <div style={{
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
            flexWrap: "wrap",
            height: "100vh"
        }}>
            <Nav text="Acão" destino="/acao"/>
            
        </div>
    )
}