import Link from "next/link"

export default function Home() {
    return (
        <div style={{
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
            flexWrap: "wrap",
            height: "100vh"
        }}>

            <Link href="/acao">
                <div style={{backgroundColor: "dodgerblue", padding: "30px", margin: "10", borderRadius: "8px"}}>
                    Ação
                </div>
            </Link>
        </div>
    )
}