const { Sequelize, DataTypes } = require('sequelize');
const sequelize = require("../database/connection");

const User = sequelize.define('User', {
    id: {
        type: DataTypes.INTEGER,
        autoIncrement: true,
        primaryKey: true
    },
    name: {
        type: DataTypes.STRING(50),
        allowNull: false,
        validate: {
            notNull: { msg: 'User must have a password' },
            notEmpty: { msg: 'password must not be empty' },
        }
    },
    email: {
        type: DataTypes.STRING(50),
        allowNull: false,
        unique: true,
        validate: {
            notNull: { msg: 'User must have a email' },
            notEmpty: { msg: 'email must not be empty' },
            isEmail: { msg: 'Must be a valid email address' }
        }
    },
    password: {
        type: DataTypes.STRING(1024),
        allowNull: false,
        validate: {
            notNull: { msg: 'User must have a password' },
            notEmpty: { msg: 'password must not be empty' },
        }
    },
    phoneNumber: {
        type: DataTypes.STRING(50),
    }
});

module.exports = User;