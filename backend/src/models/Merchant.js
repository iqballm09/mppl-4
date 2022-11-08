const Sequelize = require("sequelize");
const sequelize = require("../database/connection");

const Merchant = sequelize.define("Merchant", {
    id: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    username: {
        type: Sequelize.STRING(100),
        unique: true,
        allowNull: false,
        validate: {
            notEmpty: { msg: "Username must have value" },
            notNull: { msg: "Username must not null" }
        }
    },
    name: {
        type: Sequelize.STRING(500),
        allowNull: false,
        validate: {
            notEmpty: { msg: "name must not be empty" }
        }
    },
    password: {
        type: Sequelize.STRING(1024),
        validate: {
            notEmpty: { msg: "password must not be empty" }
        }
    },
    email: {
        type: Sequelize.STRING(100),
        unique: true,
        validate: {
            isEmail: { msg: 'Must be a valid email address' }
        }
    },
    phoneNumber: {
        type: Sequelize.STRING(50)
    },
    location: {
        type: Sequelize.STRING(1000)
    },
    income: {
        type: Sequelize.DECIMAL(20),
        defaultValue: 0
    }
});

// Define association
Merchant.associate = function (models) {
    Merchant.hasMany(models.Menu, { foreignKey: "MerchantID" });
    Merchant.hasMany(models.Order, { foreignKey: "MerchantID" });
}

module.exports = Merchant;