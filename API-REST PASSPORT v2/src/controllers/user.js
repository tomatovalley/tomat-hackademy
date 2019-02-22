const User = require("../models/User");
const bcrypt = require("bcrypt");
const jwt = require("jsonwebtoken");
const config = require('../config');
const forgot = require('password-reset')({
    uri : 'http://localhost:3000/password_reset',
    from : 'password-robot@localhost',
    host : 'localhost', port : 3000,
});
const { Pool, Client } = require('pg');

const client = new Client({
  user: 'hackamigo',
  host: '157.230.182.120',
  database: 'tomatohack',
  password: 'Tomato123'
})



module.exports = {

    index: async (req, res, next) => {
        const users = await User.find({});
        res.status(200).json(users);
    },

    newUser: async(req, res, next)=>{
        const newUser = new User(req.body);
        const user = await newUser.save();  
        res.status(200).json(user); 
    },

    getUser: async (req, res, next)=>{
       const { userId } = req.params;
       const user = await User.findById(userId);
       res.status(200).json(user);
    },

    replaceUser: async(req, res, next) =>{
        const { userId } = req.params;
        const newUser = req.body;
        const oldUser = await User.findByIdAndUpdate(userId, newUser);
        res.status(200).json({success: true});
    },

    updateUser: async(req, res, next) =>{
        const { userId } = req.params;
        const newUser = req.body;
        const oldUser = await User.findByIdAndUpdate(userId, newUser);
        res.status(200).json({success: true});
    },

    deleteUser: async(req, res, next) =>{
        const { userId } = req.params;
        await User.findByIdAndRemove(userId);
        res.status(200).json({success: true});
    },

    loginUser: async(req, res, next)=>{
        User.find({ email: req.body.email })
        .exec()
        .then(user => {
            if(user.length < 1){
                return res.status(401).json({
                    message: "Auth fallida"
                });
            }
            bcrypt.compare(req.body.password, user[0].password, (err, result) => {
                //console.log(req.body.password);
                //console.log(user[0].password);
                var userId = "";
                if(err){
                    return res.status(401).json({
                        message: "Auth fallida"
                    });
                }
                if(result){
                    const token = jwt.sign({
                      email: user[0].email,
                      userId: user[0]._id
                    }, config.secret, {
                            expiresIn: "1h"
                        } 
                    );
                    token: token
                    //userId = token;
                    user[0].id = token;
                    //console.log(user[0].id);
                    console.log(token);
                    return res.status(200).json(user);
                    /*return res.status(200).json({
                        message: "Auth successful",
                        token: token
                    })*/
                    
                }
                if(req.body.password!=user[0].password){
                    return res.status(401).json({
                        message: "Auth fallida"
                    });
                }
            });
        })
        .catch(err => {
            console.log(err);
            res.status(500).json({
                error: err
            });
        });
    },

    newUser2: async(req, res, next) => {
        User.find({ email: req.body.email })
          .exec()
          .then(user => {
            if (user.length >= 1) {
              return res.status(409).json({
                message: "El email ya se encuentra registrado"
              });
            } else {
              bcrypt.hash(req.body.password, 10, (err, hash) => {
                if (err) {
                  return res.status(500).json({
                    error: err
                  });
                } else {
                  const data = {text: req.body.name, text: req.body.user_name, text: req.body.email, complete: false};
                  const user = new User({
                    //_id: new mongoose.Types.ObjectId(),
                    name: req.body.name,
                    last_name: req.body.last_name,
                    user_name: req.body.user_name,
                    email: req.body.email,
                    password: hash,
                    gender: req.body.gender,
                    birthdate: req.body.birthdate
                  });
                  client.query('INSERT INTO auth_user(name, user_name, email) values($1, $2, $3)',
    [               data.text, data.complete]);
                  user
                    .save()
                    .then(result => {
                      console.log(result);
                      /*res.status(201).json({
                        message: "User created"
                      });*/
                      res.status(200).json(user);
                    })
                    .catch(err => {
                      console.log(err);
                      res.status(500).json({
                        error: err
                      });
                    });
                }
              });
            }
          });
      },
      logout: async(req, res, next) =>{
        if (!req.accessToken) return res.sendStatus(401); //return 401:unauthorized if accessToken is not present
        User.logout(req.accessToken.id, function(err) {
            if (err) return next(err);
            //res.redirect('/'); //on successful logout, redirect
        });
      },
      facebookOAuth: async(req, res, next)=>{
      },
      loginUserGoogle: async(req, res, next)=>{
        User.find({ email: req.body.email })
        .exec()
        .then(user => {
            if(user.length < 1){
                return res.status(401).json({
                    message: "Auth fallida"
                });
            }
            
        })
        .catch(err => {
            console.log(err);
            res.status(500).json({
                error: err
            });
        });
      }



};