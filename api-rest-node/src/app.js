const bodyParser = require("body-parser");
const morgan = require("morgan");
const express = require("express");
const mongoose = require("mongoose");

const app = express();

const usersRoutes = require("./routes/users");

mongoose.Promise = global.Promise;
mongoose.connect("mongodb://10.112.31.201/tomato", {
    useMongoClient: true
}).then(db => console.log("db is connected"))
    .catch(err => console.log(err));
/*mongoose.connect("mongodb://localhost/rest-api-example", {
    useMongoClient: true
}).then(db => console.log("db is connected"))
    .catch(err => console.log(err));*/

//settings
app.set("port",process.env.PORT || 3000);

//middlewares
app.use(morgan("dev"));
app.use(bodyParser.json());

// routes
app.use("/users", usersRoutes);

// static files

// start the server
app.listen(app.get("port"),() =>{
    console.log("Server on port",app.get("port"));
});