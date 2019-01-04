const bodyParser = require("body-parser");
const morgan = require("morgan");
const express = require("express");
const mongoose = require("mongoose");
const session = require("express-session");
const flash = require("connect-flash");
const passport = require("passport");
const cors = require("cors");

const app = express();

const usersRoutes = require("./routes/users");
require("./config/passport");

mongoose.Promise = global.Promise;
/*mongoose.connect("mongodb://10.112.31.201/tomato", {
    useCreateIndex: true,
    useNewUrlParser: true,
    useFindAndModify: false
}).then(db => console.log("db is connected"))
    .catch(err => console.log(err));*/
mongoose.connect("mongodb://localhost/rest-api-example", {
    useCreateIndex: true,
    useNewUrlParser: true,
    useFindAndModify: false
}).then(db => console.log("db is connected"))
    .catch(err => console.log(err));

//settings
app.set("port",process.env.PORT || 3000);
app.use(cors({origin: "http://localhost:4200"}));
//middlewares
app.use(passport.initialize());
app.use(passport.session());
app.use(morgan("dev"));
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());
app.use(function(req, res, next) {
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Authorization, Content-Type, Accept");
    next();
});

// routes
app.use("/users", usersRoutes);

// static files
/*app.use((req, res, next)=>{
    res.locals.success_msg = req.flash("success_msg");
    res.locals.error_msg = req.flash("error_msg");
    res.locals.error = req.flash("error");
    res.locals.user = req.user || null;
    next();
});*/

// start the server
app.listen(app.get("port"),() =>{
    console.log("Server on port",app.get("port"));
});
//http://10.112.31.201:3000/users
