$ ->
  my.initAjax()

  Glob = window.Glob || {}

  apiUrl =
    addDoctor: '/doc/add-doctor'
    getRole: '/admin/get-roles'

  defaultDoctor =
    firstName: ''
    lastName: ''
    email: ''
    phone: ''
    login: ''
    role: ''
    companyCode: ''

  vm = ko.mapping.fromJS
    doctor: defaultDoctor
    customerId: ''
    language: Glob.language
    doctorLogin: ''
    doctorPassword: ''
    getRoleTypeList: []

  handleError = (error) ->
    $.unblockUI()
    if error.status is 401
      my.logout()
    else if error.status is 500 or (error.status is 400 and error.responseText)
      toastr.error(error.responseText)
    else
      toastr.error('Something went wrong! Please try again.')

  $('#show_hide_password a').on 'click', (event) ->
    event.preventDefault()
    if $('#show_hide_password input').attr('type') == 'text'
      $('#show_hide_password input').attr 'type', 'password'
      $('#show_hide_password i').addClass 'fa-eye-slash'
      $('#show_hide_password i').removeClass 'fa-eye'
    else if $('#show_hide_password input').attr('type') == 'password'
      $('#show_hide_password input').attr 'type', 'text'
      $('#show_hide_password i').removeClass 'fa-eye-slash'
      $('#show_hide_password i').addClass 'fa-eye'

  vm.getRoleType = ->
    $.get(apiUrl.getRole)
    .fail handleError
    .done (response) ->
      vm.getRoleTypeList(response)

  $thankYou = $('#thankYou')

  vm.onSubmit = ->
    toastr.clear()
    if !vm.doctor.firstName()
      toastr.error("Iltimos ismni kiriting!")
      return no
    else if !vm.doctor.lastName()
      toastr.error("Iltimos familiyani kiriting!")
      return no
    else if vm.doctor.email() and !my.isValidEmail(vm.doctor.email())
      toastr.error("Iltimos emailni to'gri kiriting!")
      return no
    else if !vm.doctor.phone()
      toastr.error("Iltimos telefon raqamni kiriting!")
      return no
    else if vm.doctor.phone() and !my.isValidPhone(vm.doctor.phone().replace(/[(|)|-]/g, "").trim())
      toastr.error("Iltimos telefon raqamni to'gri kiriting!")
      return no
    else if !vm.doctor.login()
      toastr.error("Iltimos loginni kiriting!")
      return no
    else if !vm.doctor.role()
      toastr.error("Iltimos tizimdagi vazifasini tanlang!")
      return no
    else
      doctor =
        firstName: vm.doctor.firstName()
        lastName: vm.doctor.lastName()
        email: vm.doctor.email()
        phone: vm.doctor.phone().replace(/[(|)|-]/g, "").trim()
        login: vm.doctor.login()
        role: vm.doctor.role()
        company_code: window.location.host
      $.post(apiUrl.addDoctor, JSON.stringify(doctor))
      .fail handleError
      .done (user) ->
        vm.doctorLogin(user.login)
        vm.doctorPassword(user.password)
        ko.mapping.fromJS(defaultDoctor, {}, vm.doctor)
        $thankYou.modal('show')

  vm.translate = (fieldName) -> ko.computed () ->
    index = if vm.language() is 'en' then 0 else if vm.language() is 'ru' then 1 else if vm.language() is 'uz' then 2 else if vm.language() is 'cy' then 3 else 4
    vm.labels[fieldName][index]

  vm.labels =
    adminPanel: [
      "Admin Panel"
      "???????????? ????????????????????????????"
      "Admin boshqaruvi"
      "A???????? ??????????????????"
    ]
    login: [
      "Login"
      "??????????"
      "Login"
      "??????????"
    ]
    password: [
      "Password"
      "????????????"
      "Parol"
      "??????????"
    ]
    registrationForm: [
      "Registration Form"
      "?????????? ??????????????????????"
      "Ro'yxatdan o'tish shakli"
      "?????????????????? ???????? ??????????"
    ]
    register: [
      "Register"
      "??????????????????????"
      "Ro'yxatdan o'tish"
      "?????????????????? ????????"
    ]
    firstName: [
      "First name"
      "??????"
      "Ism"
      "??????"
    ]
    lastName: [
      "Last name"
      "??????????????"
      "Familiya"
      "??????????????"
    ]
    email: [
      "Email"
      "????. ??????????"
      "Email"
      "??????????"
    ]
    phoneNumber: [
      "Phone number"
      "???????????????????? ??????????"
      "Telefon raqami"
      "?????????????? ????????????"
    ]
    selectRole: [
      "Select role"
      "???????????????? ????????"
      "Rolni tanlang"
      "?????????? ??????????????"
    ]
    thankYou: [
      "Thank you!"
      "??????????????!"
      "Rahmat! Siz ro'yxatdan o'tdingiz!"
      "????????????! ?????? ?????????????????? ????????????????!"
    ]
    closeModal: [
      "Close"
      "??????????????"
      "Yopish"
      "????????"
    ]

  ko.applyBindings {vm}