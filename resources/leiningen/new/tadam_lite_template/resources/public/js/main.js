document.addEventListener('DOMContentLoaded', () => {

    // States:
    // stop - quiet
    // play - run script
    // yes - is wordpress
    // no - not is wordpress

    new Vue({
        el: '#app',
        data: {
            domain: '',
            state: 'stop'
        },
        mounted: function () {
            // Remove loading
            document.querySelector('.loading').remove();
            // Add focus input
            this.addFocusInput();
        },
        watch: {
            state: function () {
                if (this.state === 'yes') {
                    document.querySelector('body').style.backgroundColor = getComputedStyle(document.documentElement).getPropertyValue('--color-green');
                } else if (this.state === 'no') {
                    document.querySelector('body').style.backgroundColor = getComputedStyle(document.documentElement).getPropertyValue('--color-red');
                } else if (this.state === 'stop') {
                    document.querySelector('body').style.backgroundColor = getComputedStyle(document.documentElement).getPropertyValue('--color-wordpress');
                    this.domain = '';
                    this.addFocusInput();
                }
            }
        },
        methods: {
            checkDomain: function () {
                if (this.state == 'stop') {
                // Add wait message
                    this.state = 'play';
                    let that = this;
                    // Request to API
                    axios.get(`/api?domain=${this.domain}`)
                        .then(function (response) {
                            // handle success
                            that.state = response.data.result ? 'yes' : 'no';
                        })
                        .catch(function (error) {
                            // handle error
                            console.log(error);
                        });
                } else {
                    this.state = 'stop';
                }
            },
            addFocusInput: function () {
                document.querySelector('.form__input').focus();
            }
        }
    });
});
