'use strict';

/** @type {import('sequelize-cli').Migration} */
module.exports = {
  async up(queryInterface, Sequelize) {
    return queryInterface.createTable("users", {
      id: {
        type: Sequelize.INTEGER(11),
        autoIncrement: true,
        primaryKey: true
      },
      name: {
        type: Sequelize.STRING(50),
        allowNull: false,
        validate: {
          notNull: { msg: 'User must have a password' },
          notEmpty: { msg: 'password must not be empty' },
        }
      },
      email: {
        type: Sequelize.STRING(50),
        allowNull: false,
        unique: true,
        validate: {
          notNull: { msg: 'User must have a email' },
          notEmpty: { msg: 'email must not be empty' },
          isEmail: { msg: 'Must be a valid email address' }
        }
      },
      password: {
        type: Sequelize.STRING(1024),
        allowNull: false,
        validate: {
          notNull: { msg: 'User must have a password' },
          notEmpty: { msg: 'password must not be empty' }
        }
      },
      phoneNumber: {
          type: Sequelize.STRING(50),
      },
      createdAt: Sequelize.DATE,
      updatedAt: Sequelize.DATE
    });
  },

  async down(queryInterface, Sequelize) {
    return queryInterface.dropTable("users");
  }
};
