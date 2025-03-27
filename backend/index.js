// Importamos los módulos necesarios
const express = require('express') // Framework para crear el servidor web
const mysql = require('mysql2/promise') // Cliente MySQL con soporte para promesas
const path = require('path') // Módulo para trabajar con rutas de archivos
const multer = require('multer') // Middleware para manejar la subida de archivos
const fs = require('fs').promises // Módulo para manipulación de archivos con promesas

// Definimos una clase que encapsula la lógica del servidor
class ProductoServer {
    constructor() {
        this.app = express() // Creamos una instancia de Express
        this.PORT = process.env.PORT || 3000 // Definimos el puerto del servidor (por defecto 3000)

        // Inicializamos las configuraciones necesarias
        this.initMiddlewares()
        this.configureImageUpload()
        this.setupRoutes()
        this.connectDatabase()
    }

    // Configuración de middlewares
    initMiddlewares() {
        this.app.use(express.json()) // Habilita el procesamiento de JSON en las solicitudes
        this.app.use(express.urlencoded({ extended: true })) // Permite el envío de datos por formularios

        // Definir directorios públicos
        const publicDir = path.join(__dirname, 'public') // Ruta del directorio 'public'
        const imageDir = path.join(publicDir, 'imagenes') // Ruta del subdirectorio de imágenes
        
        this.app.use(express.static(publicDir)) // Servimos archivos estáticos desde 'public'
        this.app.use('/imagenes', express.static(imageDir)) // Servimos imágenes desde 'imagenes'

        // Crear el directorio de imágenes si no existe
        this.ensureImageDirectory(imageDir)
    }

    // Verifica si la carpeta de imágenes existe, si no, la crea
    async ensureImageDirectory(imageDir) {
        try {
            await fs.mkdir(imageDir, { recursive: true }) // Crea la carpeta de manera recursiva si no existe
            console.log(`Directorio de imágenes creado: ${imageDir}`)
        } catch (error) {
            console.error('Error creando directorio de imágenes:', error)
        }
    }

    // Configuración de subida de imágenes
    configureImageUpload() {
        this.storage = multer.diskStorage({
            // Define la carpeta donde se guardarán las imágenes
            destination: (req, file, cb) => {
                cb(null, 'public/imagenes/') // Guardamos las imágenes en esta ruta
            },
            // Define cómo se nombrarán los archivos subidos
            filename: (req, file, cb) => {
                const uniqueSuffix = req.body.numero_serie || Date.now() // Usamos un número de serie o la fecha actual
                const extension = path.extname(file.originalname) // Obtenemos la extensión del archivo
                cb(null, `${uniqueSuffix}${extension}`) // Guardamos el archivo con su nuevo nombre
            }
        })

        // Aplicamos las configuraciones de Multer
        this.upload = multer({ 
            storage: this.storage,
            fileFilter: this.imageFileFilter
        })
    }

    // Filtro para aceptar solo imágenes en formatos específicos
    imageFileFilter(req, file, cb) {
        const allowedTypes = ['image/jpeg', 'image/png', 'image/gif'] // Tipos permitidos
        if (allowedTypes.includes(file.mimetype)) {
            cb(null, true) // Acepta la imagen
        } else {
            cb(new Error('Tipo de archivo no válido. Solo se permiten imágenes.'), false) // Rechaza el archivo
        }
    }

    // Conectar a la base de datos MySQL
    async connectDatabase() {
        try {
            this.connection = await mysql.createConnection({
                host: 'localhost', // Servidor de la base de datos
                user: 'root', // Usuario de la base de datos
                password: '', // Contraseña (en este caso, vacía)
                database: 'db_carrito', // Nombre de la base de datos
                connectionLimit: 10 // Número máximo de conexiones simultáneas
            })
            console.log('Conexión exitosa a la base de datos')
        } catch (error) {
            console.error('Error de conexión a la base de datos:', error)
        }
    }

    // Definir las rutas del servidor
    setupRoutes() {
        // Endpoint para obtener todos los productos
        this.app.get('/productos', this.getAllProducts.bind(this)) 
    }

    // Controlador para obtener los productos desde la base de datos
    async getAllProducts(req, res) {
        try {
            // Ejecutamos la consulta SQL para obtener todos los productos
            const [productos] = await this.connection.execute('SELECT * FROM productos')
            
            // Añadimos la URL de la imagen al objeto de cada producto
            const productosConUrl = productos.map(producto => ({
                ...producto, // Copiamos los datos originales
                imagen_url: producto.imagen 
                    ? `http://ESCRIBE TU PROPIA IP:${this.PORT}/imagenes/${producto.imagen}` 
                    : null // Si no tiene imagen, asignamos null
            }))

            // Enviamos la respuesta con los productos
            res.json({
                codigo: "200",
                mensaje: "Lista Productos",
                productos: productosConUrl
            })
        } catch (error) {
            // Si hay un error, enviamos una respuesta con código 500
            res.status(500).json({
                codigo: "500", 
                mensaje: "Error al obtener productos",
                error: error.message
            })
        }
    }   

    // Inicia el servidor en el puerto configurado
    start() {
        this.app.listen(this.PORT, () => {
            console.log(`Servidor corriendo en el puerto ${this.PORT}`)
        })
    }
}

// Crear una instancia del servidor y arrancarlo
const server = new ProductoServer()
server.start()

