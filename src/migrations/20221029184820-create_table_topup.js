'use strict';

/** @type {import('sequelize-cli').Migration} */
module.exports = {
  async up(queryInterface, Sequelize) {
    return queryInterface.createTable("topups", {
      id: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
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
  },

  async down(queryInterface, Sequelize) {
    return queryInterface.dropTable("topups");
  }
};
