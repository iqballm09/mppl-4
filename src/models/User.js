const Sequelize = require('sequelize');
const sequelize = require("../database/connection");

const User = sequelize.define('User', {
    email: {
        type: Sequelize.STRING(50),
        unique: true,
        allowNull: false,
        primaryKey: true,
        validate: {
            notNull: { msg: 'User must have a email' },
            notEmpty: { msg: 'email must not be empty' },
            isEmail: { msg: 'Must be a valid email address' }
        }
    },
    name: {
        type: Sequelize.STRING(50),
        validate: {
            notEmpty: { msg: 'name must not be empty' }
        }
    },

    password: {
        type: Sequelize.STRING(1024),
        validate: {
            notEmpty: { msg: 'password must not be empty' }
        }
    },
    pinNumber: {
        type: Sequelize.STRING(1024),
        defaultValue: "000000",
        validate: {
            notEmpty: { msg: 'pin number must not be empty' },
        }
    },
    phoneNumber: {
        type: Sequelize.STRING(50),
    },
    photo: {
        type: Sequelize.STRING(1024),
        defaultValue: "default-photo.png"
    }
});

module.exports = User;