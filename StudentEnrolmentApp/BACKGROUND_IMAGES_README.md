# Background Images Setup

## Como Adicionar Imagens de Fundo

O sistema agora suporta imagens de fundo (como imagens de universidades) em todos os painéis da interface.

### Passos para Adicionar uma Imagem de Fundo:

1. **Prepare sua imagem:**
   - Escolha uma imagem de universidade (campus, biblioteca, etc.)
   - Formatos suportados: JPG, PNG, GIF
   - Recomendado: Resolução mínima de 1000x600 pixels

2. **Adicione a imagem ao projeto:**
   - Coloque sua imagem na pasta: `src/resources/backgrounds/`
   - Nomeie o arquivo como: `university.jpg` (ou altere o nome no código)

3. **Exemplo de estrutura de pastas:**
   ```
   StudentEnrolmentApp/
   ├── src/
   │   ├── resources/
   │   │   ├── backgrounds/
   │   │   │   └── university.jpg  ← Sua imagem aqui
   │   │   └── icons/
   │   │       ├── course.png
   │   │       ├── enroll.png
   │   │       └── ...
   ```

### Onde Encontrar Imagens de Universidades:

1. **Sites de Imagens Gratuitas:**
   - [Unsplash](https://unsplash.com/s/photos/university)
   - [Pexels](https://www.pexels.com/search/university/)
   - [Pixabay](https://pixabay.com/images/search/university/)

2. **Termos de Busca Sugeridos:**
   - "university campus"
   - "college building"
   - "library interior"
   - "graduation hall"
   - "academic building"

### Personalização:

Se você quiser usar um nome de arquivo diferente ou múltiplas imagens para diferentes painéis:

1. Abra os arquivos em `src/ui/`:
   - `AddStudentPanel.java`
   - `ViewStudentsPanel.java`
   - `SearchStudentPanel.java`
   - `AddCoursePanel.java`
   - `EnrolStudentPanel.java`

2. Localize a linha no construtor:
   ```java
   super("/resources/backgrounds/university.jpg");
   ```

3. Altere o nome do arquivo conforme necessário:
   ```java
   super("/resources/backgrounds/minha_imagem.png");
   ```

### Ajustar a Opacidade da Imagem:

A imagem de fundo é exibida com 15% de opacidade para não interferir na legibilidade.

Para ajustar a opacidade, edite o arquivo `src/ui/BackgroundPanel.java`:

```java
// Linha 27 - Altere o valor 0.15f (15%) para o desejado
g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.15f));
```

Valores sugeridos:
- `0.10f` = 10% (mais sutil)
- `0.15f` = 15% (padrão)
- `0.25f` = 25% (mais visível)
- `0.50f` = 50% (muito visível)

### Notas Importantes:

- Se a imagem não for encontrada, o painel será exibido normalmente sem imagem de fundo
- A imagem será redimensionada automaticamente para caber no painel
- Certifique-se de que a imagem está no formato correto e no caminho correto
