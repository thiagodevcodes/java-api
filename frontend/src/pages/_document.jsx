import { Html, Head, Main, NextScript} from 'next/document'

export default function Document() {
  return (
    <Html lang="pt-br">
      <title>Orçamento Público</title>
      <link rel="icon" href="/icons/favicon.svg" type="image/x-icon"></link>
      <Head/>
      <body>
        <Main />
        <NextScript />
      </body>
    </Html>
  )
}
