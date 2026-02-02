$(function(){

// User Register validation

	var $userRegister=$("#userRegister");

	$userRegister.validate({

		rules:{
			name:{
				required:true,
				lettersonly:true
			}
			,
			email: {
				required: true,
				space: true,
				email: true
			},
			mobileNumber: {
				required: true,
				space: true,
				numericOnly: true,
				minlength: 10,
				maxlength: 12

			},
			password: {
				required: true,
				space: true,
				minlength: 8

			},
			confirmPassword: {
				required: true,
				space: true,
				equalTo: '#pass'

			},
			address: {
				required: true,
				all: true

			},

			city: {
				required: true,
//				space: true

			},
			state: {
				required: true,


			},
			pincode: {
				required: true,
				space: true,
				numericOnly: true

			}, img: {
				required: true,
			}

		},
		messages:{
			name:{
				required:'name required',
				lettersonly:'invalid name'
			},
			email: {
				required: 'email name must be required',
				space: 'space not allowed',
				email: 'Invalid email'
			},
			mobileNumber: {
				required: 'mob no must be required',
				space: 'space not allowed',
				numericOnly: 'invalid mob no',
				minlength: 'min 10 digit',
				maxlength: 'max 12 digit'
			},

			password: {
				required: 'password must be required',
				space: 'space not allowed',
				minlength: 'Min 8 digit'

			},
			confirmPassword: {
				required: 'confirm password must be required',
				space: 'space not allowed',
				equalTo: 'password mismatch'

			},
			address: {
				required: 'address must be required',
				all: 'invalid'

			},

			city: {
				required: 'city must be required',
//				space: 'space not allowed'

			},
			state: {
				required: 'state must be required',
				space: 'space not allowed'

			},
			pincode: {
				required: 'pincode must be required',
				space: 'space not allowed',
				numericOnly: 'invalid pincode'

			},
			img: {
				required: 'image required',
			}
		}
	})


    // Orders Validation

    var $orders=$("#orders");

    $orders.validate({
            rules:{
                firstName:{
                    required:true,
                    lettersonly:true
                },
                lastName:{
                    required:true,
                    lettersonly:true
                }
                ,
                email: {
                    required: true,
                    space: true,
                    email: true
                },
                mobileNo: {
                    required: true,
                    space: true,
                    numericOnly: true,
                    minlength: 10,
                    maxlength: 12

                },
                address: {
                    required: true,
                    all: true

                },

                city: {
                    required: true,

                },
                state: {
                    required: true,
                },
                pincode: {
                    required: true,
                    space: true,
                    numericOnly: true
                },
                paymentType: {
                    required: true
                }
            },
            messages:{
                firstName:{
                    required:'first required',
                    lettersonly:'invalid name'
                },
                lastName:{
                    required:'last name required',
                    lettersonly:'invalid name'
                },
                email: {
                    required: 'email name must be required',
                    space: 'space not allowed',
                    email: 'Invalid email'
                },
                mobileNo: {
                    required: 'mob no must be required',
                    space: 'space not allowed',
                    numericOnly: 'invalid mob no',
                    minlength: 'min 10 digit',
                    maxlength: 'max 12 digit'
                }
               ,
                address: {
                    required: 'address must be required',
                    all: 'invalid'

                },

                city: {
                    required: 'city must be required'

                },
                state: {
                    required: 'state must be required',
                    space: 'space not allowed'

                },
                pincode: {
                    required: 'pincode must be required',
                    space: 'space not allowed',
                    numericOnly: 'invalid pincode'

                },
                paymentType: {
                    required: 'select payment type'
                }
            }
    })

    // Reset Password Validation

    var $resetPassword=$("#resetPassword");

    $resetPassword.validate({

            rules:{
                password: {
                    required: true,
                    space: true

                },
                confirmPassword: {
                    required: true,
                    space: true,
                    equalTo: '#pass'

                }
            },
            messages:{
               password: {
                    required: 'password must be required',
                    space: 'space not allowed'

                },
                confirmPassword: {
                    required: 'confirm password must be required',
                    space: 'space not allowed',
                    equalTo: 'password mismatch'

                }
            }
    })

    //add Product validation
    var $addProduct=$("#addProduct");
    $addProduct.validate({
        rules: {
            title: {
                required: true
            },
            description: {
                required: true
            },
            category: {
                required: true
            },
            price: {
                required: true,
                numericOnly: true
            },
            stock: {
                required: true,
                numericOnly: true,
                space: true
            },
            file: {
                required: true
            }
        },
        messages: {
            title: {
                required: 'Title must needed'
            },
            description: {
                required: 'Description must needed'
            },
            category: {
                required: 'Category must needed'
            },
            price: {
                required: 'Price must enter',
                numericOnly: 'Entered value must be numeric'
            },
            stock: {
                required: 'Stock must needed',
                numericOnly: 'Entered value must be numeric',
                space: 'Space are not allowed'
            },
            file: {
                required: 'Image must needed'
            }
        }
    })

    //Add Category validation
    var $addCategory=$("#addCategory");
    $addCategory.validate({
        rules: {
            name: {
                required: true,
                lettersonly: true
            },
            file: {
                required: true
            }
        },
        messages: {
            name: {
                required: 'Name must needed',
                lettersonly: 'No numeric value allowed'
            },
            file: {
                required: 'Image must needed'
            }
        }
    })

    // validation for add Admin
    var $addAdmin=$("#addAdmin");
    $addAdmin.validate({
        rules: {
            name: {
                required: true,
                lettersonly: true
            },
            mobileNumber: {
                required: true,
                numericOnly: true
            },
            email: {
                required: true,
                email: true,
                space: true
            },
            address: {
                required: true
            },
            city: {
                required: true,
                lettersonly: true
            },
            state: {
                required: true,
                lettersonly: true
            },
            pincode: {
                required: true,
                numericOnly: true
            },
            password: {
                required: true,
                space: true,
                minlength: 8,
                maxlength: 16
            },
            confirmPassword: {
                required: true,
                space: true,
                equalTo: '#pass'
            },
            img: {
                required: true
            }
        },
        messages: {
            name: {
                required: 'Name must required',
                lettersonly: 'Only Character are allowed'
            },
            mobileNumber: {
                required: 'Mobile no must required',
                numericOnly: 'Only numeric value are allowed'
            },
            email: {
                required: 'Email must required',
                email: 'Invalid Email ',
                space: 'Space not allowed'
            },
            address: {
                required: 'address must be required',
            },
            city: {
                required: 'City must be required',
                lettersonly: 'Only Character are allowed'
            },
            state: {
                required: 'State must be required',
                lettersonly: 'Only Character are allowed'
            },
            pincode: {
                required: 'Pincode must be required',
                numericOnly: 'Only numeric value allowed'
            },
            password: {
                required: 'Password must be required',
                space: 'Space are not allowed',
                minlength: 'min 8 digits',
                maxlength: 'max 16 digits'
            },
            confirmPassword: {
                required: 'Confirm Password must required',
                space: 'Space are not allowed',
                equalTo: 'Password mismatch'
            },
            img: {
                required: 'Image required'
            }
        }
    })

})



jQuery.validator.addMethod('lettersonly', function(value, element) {
		return /^[^-\s][a-zA-Z_\s-]+$/.test(value);
	});

		jQuery.validator.addMethod('space', function(value, element) {
		return /^[^-\s]+$/.test(value);
	});

	jQuery.validator.addMethod('all', function(value, element) {
		return /^[^-\s][a-zA-Z0-9_,.\s-]+$/.test(value);
	});


	jQuery.validator.addMethod('numericOnly', function(value, element) {
		return /^[0-9]+$/.test(value);
	});