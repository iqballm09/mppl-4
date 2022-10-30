const Sequelize = require("Sequelize");
const sequelize = require("../database/connection");

const TopUp = sequelize.define("TopUp", {
    id: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    CardID: {
        type: Sequelize.INTEGER
    },
    amount: {
        type: Sequelize.DECIMAL(20),
        defaultValue: 0,
        allowNull: false,
        validate: {
            notEmpty: { msg: "Amount must have value" }
        }
    },
    method: {
        type: Sequelize.STRING(50)
    },
    detailMethod: {
        type: Sequelize.STRING(1024)
    },
    date: {
        type: Sequelize.DATEONLY
    }
});

module.exports = TopUp;