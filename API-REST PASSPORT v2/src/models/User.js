const mongoose = require("mongoose");
const Schema = mongoose.Schema;
const bcrypt = require("bcryptjs");


const userSchema = new Schema({
    id: {type: String, required: false},
    name: { type: String, required: false },
    last_name: { type: String, required: false },
    user_name: { type: String, required: false },
    password: { type: String, required: false},
    birthdate: { type: Date, required: false},
    email: {
        type: String,
        required: false,
        unique: false,
        match: /[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/
    },
    gender: { type: String, required: false}
});
userSchema.methods.encryptPassword = async (password)=>{
    const salt = await bcrypt.genSalt(10);
    const hash = bcrypt.hash(password, salt);
    return hash;
};

userSchema.methods.matchPassword = async function (password){
    return await bcrypt.compare(password, this.password)
}

module.exports = mongoose.model("User", userSchema);
